package com.Clinic_Accounting_System.commands.admin;

import com.Clinic_Accounting_System.interfaces.Command;
import com.Clinic_Accounting_System.dao.AppointmentDAO;
import com.Clinic_Accounting_System.dao.DoctorDAO;
import com.Clinic_Accounting_System.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DeleteDoctor implements Command {

    private final AppointmentDAO appointmentDAO = AppointmentDAO.getInstance();
    private final DoctorDAO doctorDAO = DoctorDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // get docID param from request
        Long docID = Long.parseLong(request.getParameter("docID"));

        // remove all doc's appointments
        appointmentDAO.getAppointmentsByDocId(docID);

        // remove doc from database, cascade will do the rest with other doctor's dependencies
        doctorDAO.removeById(docID);

        // notify about successful delete operation
        ControllerUtils.giveTicketToMyMessage(request, "Doctor successfully deleted from database!");

        return "redirect:/admin/doctors";
    }

}
