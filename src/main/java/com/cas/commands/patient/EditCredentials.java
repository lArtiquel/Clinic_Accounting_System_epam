package com.cas.commands.patient;

import com.cas.interfaces.Controller;
import com.cas.interfaces.Command;
import com.cas.dao.UserDAO;
import com.cas.entities.User;
import com.cas.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller(path = "/patient/EditCredentials",
        description = "Edit credentials and redirect back to the account page.")
public class EditCredentials implements Command {

    private final UserDAO userDAO = UserDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // fetch params from request
        String newUsername = request.getParameter("newUsername");
        String newPassword = request.getParameter("newPassword");

        // fetch user from db
        User user = userDAO.getById((Long)request.getSession().getAttribute("user_id"));

        if(user != null){
            if(!user.getUsername().equals(newUsername) || !user.getPassword().equals(newPassword)){
                if(!userDAO.isNewUsernameAvailable(newUsername, user.getUsername())){
                    // update username and password parameters
                    userDAO.updateUsernameAndPassword(user.getId(), newUsername, newPassword);
                    // give ticket to successful username and password update message
                    ControllerUtils.giveTicketToMyMessage(request, "Username and password updated!");
                } else {
                    // user entered same credentials
                    ControllerUtils.giveTicketToMyMessage(request, "User with such username already exists!");
                }
            } else {
                // user entered same credentials
                ControllerUtils.giveTicketToMyMessage(request, "You entered same credentials!");
            }
        } else {
            return ControllerUtils.processNonexistentUserWithValidSessionParams(request, response);
        }

        return "redirect:/patient/account";
    }

}
