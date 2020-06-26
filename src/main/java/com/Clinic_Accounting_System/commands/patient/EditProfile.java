package com.Clinic_Accounting_System.commands.patient;

import com.Clinic_Accounting_System.commands.Command;
import com.Clinic_Accounting_System.dao.PatientDAO;
import com.Clinic_Accounting_System.entities.Patient;
import com.Clinic_Accounting_System.utils.ControllerUtils;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
