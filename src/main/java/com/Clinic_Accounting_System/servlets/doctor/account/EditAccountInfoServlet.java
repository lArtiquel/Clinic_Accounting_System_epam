package com.Clinic_Accounting_System.servlets.doctor.account;

import com.Clinic_Accounting_System.dao.UserDAO;
import com.Clinic_Accounting_System.entities.User;
import com.Clinic_Accounting_System.utils.ControllerUtils;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(name = "DoctorsEditAccountInfoServlet", urlPatterns = "/doctor/editAccountInfo")
public class EditAccountInfoServlet extends HttpServlet {

    private final UserDAO userDAO = UserDAO.getInstance();

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            HttpSession session = request.getSession();
            Long userID = (Long) session.getAttribute("user_id");
            String oldUsername = request.getParameter("oldUsername");
            String oldPassword = request.getParameter("oldPassword");
            String newUsername = request.getParameter("newUsername");
            String newPassword = request.getParameter("newPassword");

            if(!oldUsername.equals(newUsername) || !oldPassword.equals(newPassword)){
                User user = userDAO.getById(userID);
                if(user != null){
                    // reset username and password parameters
                    userDAO.updateUsernameAndPassword(userID, newUsername, newPassword);
                    // notify user about successful update
                    ControllerUtils.giveTicketToMyMessage(session, "Credentials updated!");
                    response.sendRedirect("/doctor/account");
                } else {
                    ControllerUtils.processNonexistentUserWithValidSessionParams(session, request, response);
                }
            } else {
                // if user entered same params redirect at the same page and display an error message
                ControllerUtils.giveTicketToMyMessage(session, "You entered same credentials!");
                response.sendRedirect("/doctor/account");
            }
        } catch(SQLException e) {
            log.error("500: SQLException at doctor/account/EditAccountInfoServlet");
            response.sendRedirect("/errors/500.html");
        }
    }

}
