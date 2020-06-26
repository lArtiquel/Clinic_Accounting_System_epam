package com.Clinic_Accounting_System.commands.doctor;

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
import java.sql.SQLException;

public class EditPatientsMedicalHistory implements Command {

    private final PatientDAO patientDAO = PatientDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // retrieve params from request
        Long patientID = Long.parseLong(request.getParameter("patientID"));
        String newMedicalHistory = request.getParameter("newMedicalHistory");

        // fetch patient from db
        Patient patient = patientDAO.getPatientById(patientID);

        if(patient != null){
            // update patient's medical history
            patientDAO.updatePatientMedicalHistoryById(patient.getId(), newMedicalHistory);
            ControllerUtils.giveTicketToMyMessage(request, "Patient's medical history successfully updated!");
        } else {
            ControllerUtils.giveTicketToMyMessage(request, "Patient account does not exist anymore!");
        }

        return "redirect:/doctor/patients";
    }

}
