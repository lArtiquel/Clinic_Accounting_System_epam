package com.Clinic_Accounting_System.commands.admin;

import com.Clinic_Accounting_System.interfaces.Command;
import com.Clinic_Accounting_System.dao.AppointmentDAO;
import com.Clinic_Accounting_System.entities.Appointment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class Appointments implements Command {

    private final AppointmentDAO appointmentDAO = AppointmentDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
            // fetch from database all appointments
            List<Appointment> appointments = appointmentDAO.getAll();

            // push list of appointments into request scope
            request.setAttribute("appointments", appointments);

            return "forward:/pages/admin/appointments.jsp";
    }

}
