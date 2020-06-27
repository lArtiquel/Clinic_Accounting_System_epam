package com.Clinic_Accounting_System.commands.registration;

import com.Clinic_Accounting_System.interfaces.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class RegistrationForm implements Command
{

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        return "forward:/pages/registration/registration-form.jsp";
    }

}
