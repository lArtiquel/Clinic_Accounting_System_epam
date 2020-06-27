package com.Clinic_Accounting_System.commands.patient;

import com.Clinic_Accounting_System.interfaces.Command;
import com.Clinic_Accounting_System.dao.PatientDAO;
import com.Clinic_Accounting_System.entities.Patient;
import com.Clinic_Accounting_System.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class Profile implements Command {

    private final PatientDAO patientDAO = PatientDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // fetch patient from db
        Patient patient = patientDAO.getPatientById((Long)request.getSession().getAttribute("user_id"));

        if(patient != null){

            // set request attribs, so jsp can render the page
            request.setAttribute("firstname", patient.getFirstname());
            request.setAttribute("midname", patient.getMiddlename());
            request.setAttribute("lastname", patient.getLastname());
            request.setAttribute("email", patient.getEmail());
            request.setAttribute("phone", patient.getPhone());
            request.setAttribute("dob", patient.getDob());
            request.setAttribute("address", patient.getAddress());
            request.setAttribute("medHistory", patient.getMedicalHistory());

            return "forward:/pages/patient/profile.jsp";
        } else {
            return ControllerUtils.processNonexistentUserWithValidSessionParams(request, response);
        }
    }

}
