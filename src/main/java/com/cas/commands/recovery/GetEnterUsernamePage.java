package com.cas.commands.recovery;

import com.cas.interfaces.Controller;
import com.cas.interfaces.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller(path = "/recovery/enter-username",
        description = "Return page where user can enter username of account to restore access to.")
public class GetEnterUsernamePage implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        return "forward:/pages/recovery/enter-username.jsp";
    }

}
