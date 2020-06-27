package com.Clinic_Accounting_System.commands.recovery;

import com.Clinic_Accounting_System.interfaces.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class EnterUsernameServlet implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        return "forward:/pages/recovery/enter-username.jsp";
    }

}
