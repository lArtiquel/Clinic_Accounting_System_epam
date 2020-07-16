package com.cas.servlets;

import com.cas.commands.CommandFactory;
import com.cas.interfaces.Command;
import com.cas.utils.ControllerUtils;
import lombok.extern.log4j.Log4j2;

import javax.servlet.DispatcherType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(name = "DispatcherServlet",
        urlPatterns = "/app/*", // app prefix added in filter, so don't add it in the browser
        description = "Arti's CAS Dispatcher servlet.",
        loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet{

    // same prefix as this servlet mapped to
    public static final String SERVLET_PREFIX = "/app";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
            My invention:
                Every get request should go thru `MessageByTicket` system
                It needed to display message popups only once on the client.
         */
        ControllerUtils.goThru_MessageByTicket_System(request);
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /*
        Determine command by provided path.
        Explanation:
            if URI is https://art.com/CAS/app/admin/home
            where art.com/CAS is contextPath
            so, path should be equal /app/admin/home
    */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get path without contextPath
        final String path = ControllerUtils.fetchPath(request);

        // pure path without `/app` dispatcher servlet's prefix (cut prefix)
        final String purePath = path.substring(SERVLET_PREFIX.length());

        // define command by provided path
        final Command command = CommandFactory.getCommand(purePath);

        // location to send user to:)
        String action = "";

        // load corresponding command class thru classloader and run execute method
        try {
            if(command != null){
                // command found
                action = command.execute(request, response);
            } else {
                // unknown command -> display 404
                action = "forward:/pages/errors/404.jsp";
            }
        } catch (SQLException e) {
            log.error(command.getClass().getName() + " class threw SQLException." +
                    "Message: " + e.getMessage());
            action = "forward:/pages/errors/500.jsp";
        } catch (Exception e) {
            log.error(command.getClass().getName() + " class threw Exception." +
                    "Message: " + e.getMessage());
            action = "forward:/pages/errors/500.jsp";
        } finally {
            // check returned action on correctness
            if(!isActionStrCorrect(action)) {
                log.error("Action string is not correct!");
                action = "forward:/pages/errors/500.jsp";
            }

            // dispatch action
            if(action.startsWith("forward:")){
                request.getRequestDispatcher(action.substring("forward:".length())).forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + action.substring("redirect:".length()));
            }
        }
    }

    private boolean isActionStrCorrect(String action) {
        // correct path starts with either "redirect:/" or "forward:/"
        return action.startsWith("redirect:/") || action.startsWith("forward:/");
    }
}
