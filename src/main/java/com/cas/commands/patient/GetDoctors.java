package com.cas.commands.patient;

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
        // fetch all doctors from db
        List<Doctor> doctors = doctorDAO.getAll();

        // set list of doctors as request attrib
        request.setAttribute("doctors", doctors);

        return "forward:/pages/patient/doctors.jsp";
    }

}
