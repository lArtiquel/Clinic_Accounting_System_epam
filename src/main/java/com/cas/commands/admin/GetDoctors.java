package com.cas.commands.admin;

import com.cas.interfaces.Command;
import com.cas.dao.DoctorDAO;
import com.cas.entities.Doctor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class GetDoctors implements Command {

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
