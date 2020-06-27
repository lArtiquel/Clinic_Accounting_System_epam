package com.Clinic_Accounting_System.commands.admin;

import com.Clinic_Accounting_System.interfaces.Command;
import com.Clinic_Accounting_System.dao.DoctorDAO;
import com.Clinic_Accounting_System.entities.Doctor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class Doctors implements Command {

    private final DoctorDAO doctorDAO = DoctorDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // make call to db to fetch all doctors
        List<Doctor> doctorList = doctorDAO.getAll();

        // push list of doctors into request scope
        request.setAttribute("doctors", doctorList);

        // process thru message-by-ticket system
        return "forward:/pages/admin/doctors.jsp";
    }

}
