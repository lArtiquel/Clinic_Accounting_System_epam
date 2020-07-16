package com.cas.commands.doctor;

import com.cas.interfaces.Controller;
import com.cas.interfaces.Command;
import com.cas.dao.PatientDAO;
import com.cas.entities.Patient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@Controller(path = "/doctor/patients",
        description = "Return page with patients.")
public class GetPatients implements Command {

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