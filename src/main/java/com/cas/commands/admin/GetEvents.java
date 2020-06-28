package com.cas.commands.admin;

import com.cas.interfaces.Command;
import com.cas.dao.EventDAO;
import com.cas.entities.Event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class GetEvents implements Command {

    private final EventDAO eventDAO = EventDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // fetch all events
        List<Event> events = eventDAO.getAll();

        // push list of events into request scope
        request.setAttribute("events", events);

        return "forward:/pages/admin/events.jsp";
    }

}
