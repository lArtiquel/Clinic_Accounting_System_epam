package com.cas.commands.doctor;

import com.cas.interfaces.Controller;
import com.cas.interfaces.Command;
import com.cas.dao.PatientDAO;
import com.cas.entities.Patient;
import com.cas.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller(path = "/doctor/EditPatientsMedicalHistory",
        description = "Edit patient's medical history and redirect back to the patients page.")
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
