package com.cas.commands.admin;

import com.cas.interfaces.Controller;
import com.cas.interfaces.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Controller(path = "/admin/home",
        description = "Return home page.")
public class GetHome implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        return "forward:/pages/admin/home.jsp";
    }
}
