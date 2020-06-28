package com.cas.commands.patient;

import com.cas.interfaces.Command;
import com.cas.dao.UserDAO;
import com.cas.entities.User;
import com.cas.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class GetAccount implements Command {

    private final UserDAO userDAO = UserDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // fetch user from db
        User user = userDAO.getById((Long)request.getSession().getAttribute("user_id"));

        if(user != null){
            // set request attribs, so jsp can render the page
            request.setAttribute("username", user.getUsername());
            request.setAttribute("password", user.getPassword());
            return "forward:/pages/patient/account.jsp";
        } else {
            return ControllerUtils.processNonexistentUserWithValidSessionParams(request, response);
        }
    }

}
