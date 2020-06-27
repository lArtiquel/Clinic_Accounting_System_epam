package com.Clinic_Accounting_System.commands.auth;

import com.Clinic_Accounting_System.interfaces.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class ProcessLogoutForm implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        HttpSession session = request.getSession(false);
        if(session != null) session.invalidate();
        return "redirect:/login";
    }

}
