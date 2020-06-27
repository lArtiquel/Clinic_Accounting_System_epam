package com.Clinic_Accounting_System.commands.doctor;

import com.Clinic_Accounting_System.interfaces.Command;
import com.Clinic_Accounting_System.dao.UserDAO;
import com.Clinic_Accounting_System.entities.User;
import com.Clinic_Accounting_System.utils.ControllerUtils;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Account implements Command {

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

