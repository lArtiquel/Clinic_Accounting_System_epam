package com.Clinic_Accounting_System.servlets;

import com.Clinic_Accounting_System.commands.Command;
import com.Clinic_Accounting_System.utils.ControllerUtils;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(name = "DispatcherServlet", urlPatterns = "/*")
public class DispatcherServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
            My invention:
                Every get request should go thru `MessageByTicket` system
                It needed to display message popups only once on client
         */
        ControllerUtils.goThru_MessageByTicket_System(request);
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action; // location to send user to:)

        /*
            Get pathInfo.
            Explanation:
                if URI is art.com/CAS/admin/home
                where art.com/CAS is contextPath
                pathInfo on that dispatcher servlet should be equal /admin/home
         */
        String pathInfo = request.getPathInfo();

        /*
            Split pathInfo into pathChunks.
            I.e if the pathInfo equal /admin/home, so chunks array should look like that: '', 'admin', 'home'.
            ! Method has slight disadvantage: commands should be in only ONE folder.
            And yes, i don't want factory with all imported classes architecture.
         */
        String[] pathChunks = pathInfo.split("/");

        // load corresponding command class thru classloader and run execute method
        try {
            String className = "com.Clinic_Accounting_System.commands." + pathChunks[1] + "."
                    + pathChunks[2].substring(0, 1).toUpperCase() + pathChunks[2].substring(1); // capitalize the first letter of servlet name
            Class<?> actionClass = Class.forName(className);
            Object obj = actionClass.newInstance();
            Command command = (Command) obj;
            action = command.execute(request, response);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            log.error("Wrong path. Exception message: " + e.getMessage());
            action = "forward:/pages/errors/404.jsp";
        } catch (SQLException e) {
            log.error("SQLException. Exception message: " + e.getMessage());
            action = "forward:/pages/errors/500.jsp";
        }

        // check returned action on correctness
        try{
            if(!isActionStrCorrect(action)) throw new Exception("Wrong action format!");
        } catch (Exception e) {
            log.error(e.getMessage());
            action = "forward:/pages/errors/500.jsp";
        }

        // dispatch action
        if(action.startsWith("forward:")){
            request.getRequestDispatcher(action.substring("forward:".length())).forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + action.substring("redirect:".length()));
        }
    }

    private boolean isActionStrCorrect(String action) {
        return action.startsWith("redirect:") || action.startsWith("forward:");
    }
}
