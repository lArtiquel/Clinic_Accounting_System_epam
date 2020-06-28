package com.cas.commands.admin;

import com.cas.interfaces.Command;
import com.cas.dao.AppointmentDAO;
import com.cas.entities.Appointment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class GetAppointments implements Command {

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
