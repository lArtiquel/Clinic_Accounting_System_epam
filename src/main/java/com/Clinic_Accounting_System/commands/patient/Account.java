package com.Clinic_Accounting_System.commands.patient;

import com.Clinic_Accounting_System.commands.Command;
import com.Clinic_Accounting_System.dao.UserDAO;
import com.Clinic_Accounting_System.entities.User;
import com.Clinic_Accounting_System.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class Account implements Command {

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
