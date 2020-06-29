package com.cas.commands.admin;

import com.cas.interfaces.Controller;
import com.cas.interfaces.Command;
import com.cas.dao.PatientDAO;
import com.cas.entities.Patient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@Controller(path = "/admin/patients",
        description = "Return page with patients.")
public class GetPatients implements Command {

    private PatientDAO patientDAO = PatientDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // fetch patients from database
        List<Patient> patients = patientDAO.getAll();

        // push list of patients to the request scope
        request.setAttribute("patients", patients);

        return "forward:/pages/admin/patients.jsp";
    }

}
