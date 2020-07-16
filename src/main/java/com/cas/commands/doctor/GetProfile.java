package com.cas.commands.doctor;

import com.cas.interfaces.Controller;
import com.cas.interfaces.Command;
import com.cas.dao.DoctorDAO;
import com.cas.entities.Doctor;
import com.cas.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller(path = "/doctor/profile",
        description = "Return page with profile information.")
public class GetProfile implements Command {

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