package com.Clinic_Accounting_System.commands.admin;

import com.Clinic_Accounting_System.commands.Command;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class Home implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        return "forward:/pages/admin/home.jsp";
    }
}
