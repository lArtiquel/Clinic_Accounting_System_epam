package com.cas.commands.admin;

import com.cas.interfaces.Controller;
import com.cas.dao.EventDAO;
import com.cas.interfaces.Command;
import com.cas.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;

@Controller(path = "/admin/AddNewEvent",
        description = "Add new event and redirect back to the events page.")
public class AddNewEvent implements Command {

    private final EventDAO eventDAO = EventDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // get params from request
        String header = request.getParameter("header");
        String content = request.getParameter("content");
        Date startDate = java.sql.Date.valueOf(request.getParameter("start_date"));
        ControllerUtils.makeCorrectionForTimeZone(startDate);
        Date endDate = java.sql.Date.valueOf(request.getParameter("end_date"));
        ControllerUtils.makeCorrectionForTimeZone(endDate);
        Boolean onlyForPersonal = request.getParameterValues("only_for_personal") != null;

        // create new event
        eventDAO.createEvent(header, content, startDate, endDate, onlyForPersonal);

        // give ticket to message notifying user that event successfully created
        ControllerUtils.giveTicketToMyMessage(request, "Event successfully created!");
        return "redirect:/admin/events";
    }

}
