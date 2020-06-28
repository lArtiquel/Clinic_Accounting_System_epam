package com.cas.commands.recovery;

import com.cas.interfaces.Command;
import lombok.extern.log4j.Log4j2;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Log4j2
@WebServlet(name = "Recovery_EnterCodeServlet", urlPatterns = "/pass_recovery/enter_code")
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
