package com.cas.commands.auth;

import com.cas.interfaces.Controller;
import com.cas.interfaces.Command;
import com.cas.dao.UserDAO;
import com.cas.entities.User;
import com.cas.utils.ControllerUtils;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Log4j2
@Controller(path = "/ProcessLoginForm",
        description = "Process login form and redirect to appropriate page.")
public class ProcessLoginForm implements Command {

    private final UserDAO userDAO = UserDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // fetch params from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // check existence of username and password form attributes
        if(username == null || password == null){
            log.warn("Hacker tried to go without user/password params at sign_in.");
            return "redirect:/login";
        }

        // look for user in database thru DAO object
        User user = userDAO.getByUsernameAndPassword(username, password);

        // create new session
        HttpSession session = request.getSession(true);

        // if there is no such user in db -> stay on 'sign in' page and set error message to display
        if(user == null) {
            ControllerUtils.giveTicketToMyMessage(request, "Invalid credentials!");
            return "redirect:/login";
        } else {
            // set Auth session objects
            session.setAttribute("user_id", user.getId());
            session.setAttribute("role", user.getRole());
            // also don't forget remember-me checkbox(am i poked vain on him???)
            if(request.getParameterValues("remember-me") != null){
                // if checkbox selected --> set endless session timeout
                session.setMaxInactiveInterval(-1);
            } else{
                // set session inactive interval = 30 mins by default
                session.setMaxInactiveInterval(30*60);
            }
            // redirect user to corresponded home page
            return "redirect:/" + user.getRole() + "/home";
        }
    }

}
