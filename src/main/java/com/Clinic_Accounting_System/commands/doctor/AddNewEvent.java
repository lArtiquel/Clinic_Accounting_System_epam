package com.Clinic_Accounting_System.commands.doctor;

import com.Clinic_Accounting_System.commands.Command;
import com.Clinic_Accounting_System.dao.EventDAO;
import com.Clinic_Accounting_System.entities.Event;
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

public class AddNewEvent implements Command {

    private final EventDAO eventDAO = EventDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // get params from request
        final String header = request.getParameter("header");
        final String content = request.getParameter("content");
        final Date startDate = java.sql.Date.valueOf(request.getParameter("start_date"));
        ControllerUtils.makeCorrectionForTimeZone(startDate);
        final Date endDate = java.sql.Date.valueOf(request.getParameter("end_date"));
        ControllerUtils.makeCorrectionForTimeZone(endDate);
        final boolean onlyForPersonal = request.getParameterValues("only_for_personal") != null;

        // create new event
        eventDAO.createEvent(header, content, startDate, endDate, onlyForPersonal);

        // give ticket to message notifying user that event successfully created
        ControllerUtils.giveTicketToMyMessage(request, "Event successfully created!");
        return "redirect:/doctor/home";
    }

}
