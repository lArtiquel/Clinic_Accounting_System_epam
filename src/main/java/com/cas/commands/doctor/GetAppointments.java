package com.cas.commands.doctor;

import com.cas.interfaces.Controller;
import com.cas.dao.AppointmentDAO;
import com.cas.entities.Appointment;
import com.cas.interfaces.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@Controller(path = "/doctor/appointments",
        description = "Return page with appointments.")
public class GetAppointments implements Command {

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