package com.Clinic_Accounting_System.commands.doctor;

import com.Clinic_Accounting_System.interfaces.Command;
import com.Clinic_Accounting_System.dao.DoctorDAO;
import com.Clinic_Accounting_System.entities.Doctor;
import com.Clinic_Accounting_System.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class Profile implements Command {

    private final DoctorDAO doctorDAO = DoctorDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // fetch data from db
        Doctor doctor = doctorDAO.getDoctorById((Long)request.getSession().getAttribute("user_id"));

        if(doctor != null){
            // push doctor into request scope
            request.setAttribute("doctor", doctor);
            return "forward:/pages/doctor/profile.jsp";
        } else {
            return ControllerUtils.processNonexistentUserWithValidSessionParams(request, response);
        }
    }

}