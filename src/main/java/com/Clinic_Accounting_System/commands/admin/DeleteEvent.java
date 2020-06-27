package com.Clinic_Accounting_System.commands.admin;

import com.Clinic_Accounting_System.interfaces.Command;
import com.Clinic_Accounting_System.dao.EventDAO;
import com.Clinic_Accounting_System.utils.ControllerUtils;

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
