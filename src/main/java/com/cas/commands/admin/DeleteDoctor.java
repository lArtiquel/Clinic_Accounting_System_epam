package com.cas.commands.admin;

import com.cas.interfaces.Controller;
import com.cas.interfaces.Command;
import com.cas.dao.AppointmentDAO;
import com.cas.dao.DoctorDAO;
import com.cas.utils.ControllerUtils;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Log4j2
@Controller(path = "/admin/DeleteDoctor",
        description = "Delete doctor and redirect back to the doctors page.")
public class DeleteDoctor implements Command {

    private final AppointmentDAO appointmentDAO = AppointmentDAO.getInstance();
    private final DoctorDAO doctorDAO = DoctorDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // get docID param from request
        Long docID = 0L;
        try {
            Long.parseLong(request.getParameter("docID"));
        } catch(NumberFormatException e){
            log.error(getClass().getName() + " just threw NumberFormat exception.");
            return "forward:/pages/errors/500.jsp";
        }

        // remove all doc's appointments
        appointmentDAO.getAppointmentsByDocId(docID);

        // remove doc from database, cascade will do the rest with other doctor's dependencies
        doctorDAO.removeById(docID);

        // notify about successful delete operation
        ControllerUtils.giveTicketToMyMessage(request, "Doctor successfully deleted from database!");

        return "redirect:/admin/doctors";
    }

}
