package com.Clinic_Accounting_System.commands.recovery;

import com.Clinic_Accounting_System.interfaces.Command;
import com.Clinic_Accounting_System.dao.PatientDAO;
import com.Clinic_Accounting_System.dao.UserDAO;
import com.Clinic_Accounting_System.entities.Patient;
import com.Clinic_Accounting_System.entities.User;
import com.Clinic_Accounting_System.utils.ControllerUtils;
import com.Clinic_Accounting_System.utils.CredUtils;
import com.Clinic_Accounting_System.utils.MailBot;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

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
                return "redirect:/recovery/enterCode";
            } else {
                // if send failed
                ControllerUtils.giveTicketToMyMessage(request, "Sorry, but we cannot send your code to restore password on provided email!");
                return "redirect:/login";
            }
        } else {
            // loop back if user with such username does not exists
            ControllerUtils.giveTicketToMyMessage(request, "User with such username does not exist!");
            return "redirect:/recovery/enterUsername";
        }
    }

}
