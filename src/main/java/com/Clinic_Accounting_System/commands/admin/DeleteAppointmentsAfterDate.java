package com.Clinic_Accounting_System.commands.admin;

import com.Clinic_Accounting_System.commands.Command;
import com.Clinic_Accounting_System.dao.AppointmentDAO;
import com.Clinic_Accounting_System.utils.ControllerUtils;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
