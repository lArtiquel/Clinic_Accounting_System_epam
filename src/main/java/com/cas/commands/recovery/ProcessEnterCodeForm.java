package com.cas.commands.recovery;

import com.cas.interfaces.Controller;
import com.cas.interfaces.Command;
import com.cas.dao.UserDAO;
import com.cas.entities.User;
import com.cas.utils.ControllerUtils;
import com.cas.utils.CredUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller(path = "/recovery/ProcessEnterCodeForm",
        description = "Process entered email code and redirect to appropriate page.")
public class ProcessEnterCodeForm implements Command {

    private final UserDAO userDAO = UserDAO.getInstance();
    private final CredUtils credUtils = CredUtils.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // firstly, check if we have "forgot_page_user_id" attrib in session
        if(RecoveryRoutines.checkForgotPageUsernameAttribInSession(request)){

            // take "email_code" from request
            String code = request.getParameter("email_code");
            User user = userDAO.getById((Long)request.getSession().getAttribute("pass_recovery_user_id"));

            if(code != null && user != null){
                // if hashcode of password equals entered code
                if(code.equals(credUtils.getHash(user.getPassword()))){
                    // take new password from request scope
                    String newPassword = request.getParameter("new_password");
                    // check if password is following requirements, you can add yours
                    if(credUtils.validate(newPassword)){
                        // update password in db
                        userDAO.updatePassword(user.getId(), newPassword);
                        // notify user that password successfully changed and go sign in
                        ControllerUtils.giveTicketToMyMessage(request, "Password successfully changed!");
                        RecoveryRoutines.removeForgotPageAttributeFromSession(request);
                        return "redirect:/login";
                    } else {
                        ControllerUtils.giveTicketToMyMessage(request, "Password is less then 8 characters!");
                        return "redirect:/recovery/enter-code";
                    }
                } else {
                    ControllerUtils.giveTicketToMyMessage(request, "Wrong verification code!");
                    return "redirect:/recovery/enter-code";
                }
            } else {
                ControllerUtils.giveTicketToMyMessage(request, "Sorry, we can't restore your password.");
                RecoveryRoutines.removeForgotPageAttributeFromSession(request);
                return "redirect:/login";
            }
        } else {
            return "redirect:/recovery/enter-username";
        }
    }

}