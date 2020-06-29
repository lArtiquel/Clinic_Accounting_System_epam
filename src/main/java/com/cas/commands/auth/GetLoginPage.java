package com.cas.commands.auth;

import com.cas.interfaces.Controller;
import com.cas.interfaces.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller(path = "/login",
        description = "Return login page.")
public class GetLoginPage implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return "forward:/pages/login.jsp";
    }

}
