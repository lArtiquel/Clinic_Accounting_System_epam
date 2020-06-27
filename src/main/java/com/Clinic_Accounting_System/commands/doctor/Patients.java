package com.Clinic_Accounting_System.commands.doctor;

import com.Clinic_Accounting_System.interfaces.Command;
import com.Clinic_Accounting_System.dao.PatientDAO;
import com.Clinic_Accounting_System.entities.Patient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class Patients implements Command {

    private final PatientDAO patientDAO = PatientDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // fetch list of patients from database
        List<Patient> patients = patientDAO.getAll();

        // push patients to request
        request.setAttribute("patients", patients);

        return "forward:/pages/doctor/patients.jsp";
    }

}