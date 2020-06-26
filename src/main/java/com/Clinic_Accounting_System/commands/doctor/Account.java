package com.Clinic_Accounting_System.commands.doctor;

import com.Clinic_Accounting_System.commands.Command;
import com.Clinic_Accounting_System.dao.UserDAO;
import com.Clinic_Accounting_System.entities.User;
import com.Clinic_Accounting_System.utils.ControllerUtils;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

