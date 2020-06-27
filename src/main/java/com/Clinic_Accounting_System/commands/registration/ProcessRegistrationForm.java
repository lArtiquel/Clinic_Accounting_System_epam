package com.Clinic_Accounting_System.commands.registration;

import com.Clinic_Accounting_System.interfaces.Command;
import com.Clinic_Accounting_System.dao.PatientDAO;
import com.Clinic_Accounting_System.dao.UserDAO;
import com.Clinic_Accounting_System.entities.User;
import com.Clinic_Accounting_System.utils.ControllerUtils;
import com.Clinic_Accounting_System.utils.CredUtils;
import com.Clinic_Accounting_System.utils.Roles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;

public class ProcessRegistrationForm implements Command {

    private final UserDAO userDAO = UserDAO.getInstance();
    private final PatientDAO patientDAO = PatientDAO.getInstance();
    private final CredUtils credUtils = CredUtils.getInstance();

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // scrap creds from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // scrap userInfo from request scope
        String firstname = request.getParameter("firstname");
        String midname = request.getParameter("midname");
        String lastname = request.getParameter("lastname");
        Date dob = java.sql.Date.valueOf(request.getParameter("dob"));
        ControllerUtils.makeCorrectionForTimeZone(dob);
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String homeAddress = request.getParameter("homeAddress");

        // validate params
        if(credUtils.validate(username) && credUtils.validate(password)) {
            // check if user with such username already exists in database
            if(!userDAO.existsByUsername(username)) {
                // persist new user in database
                userDAO.createUser(username, password, Roles.patient.name());
                // persist user info in database
                User user = userDAO.getByUsername(username);
                patientDAO.createPatient(user.getId(), firstname, lastname, midname, dob,
                                            email, phone, homeAddress, "");
                // notify user about successful sign up
                ControllerUtils.giveTicketToMyMessage(request, "Successful sign up! Please, log in.");
                return "redirect:/login";
            } else {
                ControllerUtils.giveTicketToMyMessage(request, "Sorry, but user with such username already exists!");
                return "redirect:/registration/registrationForm";
            }
        } else {
            ControllerUtils.giveTicketToMyMessage(request, "Username and password length should be no less then 8 characters!");
            return "redirect:/registration/registrationForm";
        }
    }

}
