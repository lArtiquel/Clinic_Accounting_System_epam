package com.Clinic_Accounting_System.commands.admin;

import com.Clinic_Accounting_System.interfaces.Command;
import com.Clinic_Accounting_System.dao.EventDAO;
import com.Clinic_Accounting_System.entities.Event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class Events implements Command {

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
