package com.cas.commands.patient;

import com.cas.interfaces.Command;
import com.cas.dao.PatientDAO;
import com.cas.entities.Patient;
import com.cas.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;

public class EditProfile implements Command {

    private final PatientDAO patientDAO = PatientDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // fetch patient from db
        Patient patient = patientDAO.getPatientById((Long)request.getSession().getAttribute("user_id"));

        if(patient != null) {
            // get params from request
            String firstname = request.getParameter("firstname");
            String midname = request.getParameter("midname");
            String lastname = request.getParameter("lastname");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            Date dob = java.sql.Date.valueOf(request.getParameter("dob"));
            ControllerUtils.makeCorrectionForTimeZone(dob);
            String address = request.getParameter("address");

            // update patient's info in db
            patientDAO.updateAllPatientInfoExceptMedHistory(patient.getId(), firstname, lastname, midname,
                                                                dob, email, phone, address);
            // go thru message-by-ticket system
            ControllerUtils.giveTicketToMyMessage(request, "Information successfully updated!");
            return "redirect:/patient/profile";
        } else {
            return ControllerUtils.processNonexistentUserWithValidSessionParams(request, response);
        }
    }

}
