package com.Clinic_Accounting_System.commands.patient;

import com.Clinic_Accounting_System.commands.Command;
import com.Clinic_Accounting_System.dao.AppointmentDAO;
import com.Clinic_Accounting_System.dao.DoctorDAO;
import com.Clinic_Accounting_System.dao.PatientDAO;
import com.Clinic_Accounting_System.entities.Doctor;
import com.Clinic_Accounting_System.entities.Patient;
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

public class MakeAnAppointment implements Command {

    private final AppointmentDAO appointmentDAO = AppointmentDAO.getInstance();
    private final PatientDAO patientDAO = PatientDAO.getInstance();
    private final DoctorDAO doctorDAO = DoctorDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // scrap params from request and session
        Long patientID = (Long)request.getSession().getAttribute("user_id");
        Long doctorID = Long.parseLong(request.getParameter("doctorID"));
        Date date = java.sql.Date.valueOf(request.getParameter("date"));
        ControllerUtils.makeCorrectionForTimeZone(date);
        String comment = request.getParameter("comment");

        // make call to database to find out how many appointments already we have on this date
        int numberInQueue = appointmentDAO.countAppointmentsByDocIdAndDate(doctorID, date) + 1;

        // take from db all necessary to create new appointment
        Patient patient = patientDAO.getPatientById(patientID);
        Doctor doctor = doctorDAO.getDoctorById(doctorID);

        if(patient != null && doctor != null){
            // create new appointment on chosen date
            appointmentDAO.createAppointment(doctorID, patientID, numberInQueue, date, comment);
            // add give ticket to message to notify user that appointment to doctor successfully created
            ControllerUtils.giveTicketToMyMessage(request, "Appointment to doctor successfully created!");
            return "redirect:/patient/doctors";
        } else {
            return ControllerUtils.processNonexistentUserWithValidSessionParams(request, response);
        }
    }

}
