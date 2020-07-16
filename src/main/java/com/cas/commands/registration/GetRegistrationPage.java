package com.cas.commands.registration;

import com.cas.interfaces.Controller;
import com.cas.interfaces.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller(path = "/registration",
        description = "Return page where user can fill up the registration form.")
public class GetRegistrationPage implements Command
{

    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        return "forward:/pages/registration/registration-form.jsp";
    }

}
