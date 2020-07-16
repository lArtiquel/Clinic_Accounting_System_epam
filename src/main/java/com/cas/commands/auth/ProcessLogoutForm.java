package com.cas.commands.auth;

import com.cas.interfaces.Controller;
import com.cas.interfaces.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller(path = "/logout",
        description = "Logout and redirect to login page.")
public class ProcessLogoutForm implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        HttpSession session = request.getSession(false);
        if(session != null) session.invalidate();
        return "redirect:/login";
    }

}
