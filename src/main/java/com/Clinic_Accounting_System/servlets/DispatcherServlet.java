package com.Clinic_Accounting_System.servlets;

import com.Clinic_Accounting_System.interfaces.Command;
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
@WebServlet(name = "DispatcherServlet",
        urlPatterns = "/app/*", // app prefix added in filter, so don't add it in the browser
        description = "Arti's CAS Dispatcher servlet.",
        loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet{

    private final String ROOT_PACKAGE_NAME = "com.Clinic_Accounting_System";

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
        /*
            Get path.
            Explanation:
                if URI is art.com/CAS/app/admin/home
                where art.com/CAS is contextPath
                path of that dispatcher servlet should be equal /app/admin/home
         */
        final String path = ControllerUtils.fetchPath(request);

        /*
            Split pathInfo into pathChunks.
            I.e if the path equal /app/admin/home, so chunks array should look like that: 'admin', 'home' (skip '/app/').
         */
        final String[] pathChunks = path.substring("/app/".length()).split("/");

        // get full className with package
        final String className = constructClassName(pathChunks);

        // location to send user to:)
        String action = "redirect:/login";

        // load corresponding command class thru classloader and run execute method
        try {
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
        } catch (Exception e) {
            log.error("General Exception. Exception message: " + e.getMessage());
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

    private String constructClassName(String[] pathChunks){
        StringBuilder fullClassName = new StringBuilder(ROOT_PACKAGE_NAME + "." + "commands");

        if(pathChunks.length != 0){
            for(int i = 0; i < pathChunks.length; i++) {
                fullClassName.append('.');
                if(i == pathChunks.length - 1){
            /*
                Assume that last word in path is the specific Class name.
                So, capitalize the first letter of it's name cause web does not likes the capital letters.
                I.e /home is Home class, note that /Home will come to the Home to
             */
                    fullClassName.append(pathChunks[i].substring(0, 1).toUpperCase()).append(pathChunks[i].substring(1));
                } else {
                    fullClassName.append(pathChunks[i]);
                }
            }
        } else {
            // means that path is / => go on login page by default
            fullClassName.append(".Login");
        }

        return fullClassName.toString();
    }

    private boolean isActionStrCorrect(String action) {
        return action.startsWith("redirect:/") || action.startsWith("forward:/");
    }
}
