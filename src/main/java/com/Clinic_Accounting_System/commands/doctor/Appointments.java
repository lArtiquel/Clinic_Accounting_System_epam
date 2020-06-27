package com.Clinic_Accounting_System.commands.doctor;

import com.Clinic_Accounting_System.interfaces.Command;
import com.Clinic_Accounting_System.dao.AppointmentDAO;
import com.Clinic_Accounting_System.entities.Appointment;
import lombok.extern.log4j.Log4j2;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@Log4j2
@WebServlet(name = "DoctorsMyAppointmentsPageServlet", urlPatterns = "/doctor/my_appointments")
public class Appointments implements Command {

    private final AppointmentDAO appointmentDAO = AppointmentDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // get from session user_id param
        Long user_id = (Long)request.getSession().getAttribute("user_id");

        // fetch appointment's info from database
        List<Appointment> asPatientAppointments = appointmentDAO.getAppointmentsByPatientId(user_id);
        List<Appointment> asDoctorAppointments = appointmentDAO.getAppointmentsByDocId(user_id);

        // set those two lists as attributes of request scope
        request.setAttribute("asPatientAppointments", asPatientAppointments);
        request.setAttribute("asDoctorAppointments", asDoctorAppointments);

        return "forward:/pages/doctor/appointments.jsp";
    }

}