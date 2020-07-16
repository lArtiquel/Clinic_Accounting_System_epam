package com.cas.commands.doctor;

import com.cas.interfaces.Controller;
import com.cas.interfaces.Command;
import com.cas.dao.UserDAO;
import com.cas.entities.User;
import com.cas.utils.ControllerUtils;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller(path = "/doctor/account",
        description = "Return account page with valid credentials.")
public class GetAccount implements Command {

    private final UserDAO userDAO = UserDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // fetch user
        final HttpSession session = request.getSession();
        final Long id = (Long)session.getAttribute("user_id");
        final User user = userDAO.getById(id);

        if(user != null){
            // push username and pass to request scope
            request.setAttribute("username", user.getUsername());
            request.setAttribute("password", user.getPassword());

            return "forward:/pages/doctor/account.jsp";
        } else {
            return ControllerUtils.processNonexistentUserWithValidSessionParams(request, response);
        }
    }

}

