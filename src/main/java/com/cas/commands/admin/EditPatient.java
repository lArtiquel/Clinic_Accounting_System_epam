package com.cas.commands.admin;

import com.cas.interfaces.Controller;
import com.cas.interfaces.Command;
import com.cas.dao.PatientDAO;
import com.cas.dao.UserDAO;
import com.cas.entities.User;
import com.cas.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;

@Controller(path = "/admin/EditPatient",
        description = "Edit patient and redirect back to the patients page.")
public class EditPatient implements Command {

    private final UserDAO userDAO = UserDAO.getInstance();
    private final PatientDAO patientDAO = PatientDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // fetch params from request
        Long patientID = Long.parseLong(request.getParameter("patientID"));

        // check if such patient exists
        User user = userDAO.getById(patientID);
        if(user != null){
            // continue fetching
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String firstname = request.getParameter("firstname");
            String midname = request.getParameter("midname");
            String lastname = request.getParameter("lastname");
            Date dob = java.sql.Date.valueOf(request.getParameter("dob"));
            ControllerUtils.makeCorrectionForTimeZone(dob);
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String address = request.getParameter("address");

            // update data in Users
            user.setUsername(username);
            user.setPassword(password);
            userDAO.updateUsernameAndPassword(user.getId(), username, password);

            // update data in UserInfo
            patientDAO.updateAllPatientInfoExceptMedHistory(patientID, firstname, lastname, midname,
                    dob, email, phone, address);

            // give admin notification about successful update operation
            ControllerUtils.giveTicketToMyMessage(request, "Patient's info successfully updated!");
        } else {
            ControllerUtils.giveTicketToMyMessage(request, "User with such id don't exist anymore");
        }

        return "redirect:/admin/patients";
    }

}
