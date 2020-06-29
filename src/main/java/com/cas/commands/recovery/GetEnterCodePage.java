package com.cas.commands.recovery;

import com.cas.interfaces.Controller;
import com.cas.interfaces.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller(path = "/recovery/enter-code",
        description = "Return page where user can enter received email code.")
public class GetEnterCodePage implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        if(RecoveryRoutines.checkForgotPageUsernameAttribInSession(request)){
            return "forward:/pages/forgot/enter-email-code.jsp";
        } else {
            return "redirect:/recovery/enterUsername";
        }
    }

}
