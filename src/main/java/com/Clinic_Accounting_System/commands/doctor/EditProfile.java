package com.Clinic_Accounting_System.commands.doctor;

import com.Clinic_Accounting_System.commands.Command;
import com.Clinic_Accounting_System.dao.DoctorDAO;
import com.Clinic_Accounting_System.dao.PatientDAO;
import com.Clinic_Accounting_System.dao.UserDAO;
import com.Clinic_Accounting_System.entities.Doctor;
import com.Clinic_Accounting_System.entities.Patient;
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
import java.sql.Date;
import java.sql.SQLException;

public class EditProfile implements Command {

    private final DoctorDAO doctorDAO = DoctorDAO.getInstance();
    private final PatientDAO userInfoDAO = PatientDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // fetch params from request
        final Long userID = (Long)request.getSession().getAttribute("user_id");

        // fetch doctor's info from db
        final Patient userInfo = userInfoDAO.getPatientById(userID);
        final Doctor doctor = doctorDAO.getDoctorById(userID);

        if(userInfo != null && doctor != null) {
            // retrieve parameters from request
            final String firstname = request.getParameter("newFirstname");
            final String midname = request.getParameter("newMidname");
            final String lastname = request.getParameter("newLastname");
            final String email = request.getParameter("newEmail");
            final String phone = request.getParameter("newPhone");
            final Date dob = java.sql.Date.valueOf(request.getParameter("newDOB"));
            ControllerUtils.makeCorrectionForTimeZone(dob);
            final String address = request.getParameter("newAddress");
            final String specialization = request.getParameter("newSpecialization");
            final String degree = request.getParameter("newDegree");

            // update doctor's info in db
            userInfoDAO.updateAllPatientInfoExceptMedHistory(userID, firstname, lastname,
                                                    midname, dob, email, phone, address);
            doctorDAO.updateDoctor(userID, degree, specialization);

            // give ticket to message about successful update
            ControllerUtils.giveTicketToMyMessage(request, "Personal information successfully updated!");
            return "redirect:/doctor/profile";
        } else {
            return ControllerUtils.processNonexistentUserWithValidSessionParams(request, response);
        }
    }

}
