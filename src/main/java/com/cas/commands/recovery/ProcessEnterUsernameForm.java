package com.cas.commands.recovery;

import com.cas.interfaces.Controller;
import com.cas.interfaces.Command;
import com.cas.dao.PatientDAO;
import com.cas.dao.UserDAO;
import com.cas.entities.Patient;
import com.cas.entities.User;
import com.cas.utils.ControllerUtils;
import com.cas.utils.CredUtils;
import com.cas.utils.MailBot;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller(path = "/recovery/ProcessEnterUsernameForm",
        description = "Process entered username and redirect to appropriate page.")
public class ProcessEnterUsernameForm implements Command {

    private final UserDAO userDAO = UserDAO.getInstance();
    private final PatientDAO patientDAO = PatientDAO.getInstance();
    private final MailBot emailService = MailBot.getInstance();
    private final CredUtils credUtils = CredUtils.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // scrap username from request scope
        String username = request.getParameter("username");

        // check in database if user with such username exists
        User user = userDAO.getByUsername(username);
        Patient patient = patientDAO.getPatientById(user.getId());

        if(user != null){
            // then try to send code to email from profile
            if(emailService.sendMessage(patient.getEmail(),
                    "Arti's CAS password recovery system",
                    "Hey buddy, here is your code: " + credUtils.getHash(user.getPassword()) + ". Don't tell anybody!")){
                // moving "forgot_page_user_id" and "forgot_page_username" params to have access to next pages
                request.getSession().setAttribute("pass_recovery_user_id", user.getId());
                return "redirect:/recovery/enter-code";
            } else {
                // if send failed
                ControllerUtils.giveTicketToMyMessage(request, "Sorry, but we cannot send your code to restore password on provided email!");
                return "redirect:/login";
            }
        } else {
            // loop back if user with such username does not exists
            ControllerUtils.giveTicketToMyMessage(request, "User with such username does not exist!");
            return "redirect:/recovery/enter-username";
        }
    }

}
