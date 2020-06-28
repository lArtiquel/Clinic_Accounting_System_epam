package com.cas.commands.admin;

import com.cas.interfaces.Command;
import com.cas.dao.AppointmentDAO;
import com.cas.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;

public class DeleteAppointmentsAfterDate implements Command {

    private final AppointmentDAO appointmentDAO = AppointmentDAO.getInstance();

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // fetch date from request
        Date date = java.sql.Date.valueOf(request.getParameter("date"));
        ControllerUtils.makeCorrectionForTimeZone(date);    // needed because date picker returns UTC time

        // delete all appointments after date
        int numberOfDeletedAppointments = appointmentDAO.removeAppointmentsOlderThenDate(date);

        // notify admin about successful operation
        ControllerUtils.giveTicketToMyMessage(request, numberOfDeletedAppointments + " appointments successfully deleted!");

        return "redirect:/admin/appointments";
    }

}
