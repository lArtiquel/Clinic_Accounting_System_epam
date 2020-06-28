package com.cas.commands.admin;

import com.cas.interfaces.Command;
import com.cas.dao.EventDAO;
import com.cas.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DeleteEvent implements Command {

    private final EventDAO eventDAO = EventDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // get eventID param from request
        Long eventID = Long.parseLong(request.getParameter("eventID"));

        // remove event from database
        eventDAO.removeEventById(eventID);

        // notify about successful delete operation
        ControllerUtils.giveTicketToMyMessage(request, "Event successfully deleted!");

        return "redirect:/admin/events";
    }

}
