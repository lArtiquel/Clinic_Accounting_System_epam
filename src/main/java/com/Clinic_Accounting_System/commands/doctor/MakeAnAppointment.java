package com.Clinic_Accounting_System.commands.doctor;

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
        // scrap params
        final Long patientID = (Long)request.getSession().getAttribute("user_id");
        final Long doctorID = Long.parseLong(request.getParameter("doctorID"));
        final Date date = java.sql.Date.valueOf(request.getParameter("date"));
        ControllerUtils.makeCorrectionForTimeZone(date);
        final String comment = request.getParameter("comment");

        // make call to db to find out how many appointments we already have on this date
        int numberInQueue = appointmentDAO.countAppointmentsByDocIdAndDate(doctorID, date) + 1;

        // check if patient and doc with such ids are in db
        Patient patient = patientDAO.getPatientById(patientID);
        Doctor doctor = doctorDAO.getDoctorById(doctorID);
        if(patient != null && doctor != null){
            // create new appointment on chosen date
            appointmentDAO.createAppointment(doctorID, patientID, numberInQueue, date, comment);
            // give ticket to message
            ControllerUtils.giveTicketToMyMessage(request, "Appointment to selected doctor successfully created!");
            return "redirect:/doctor/doctors";
        } else {
            return ControllerUtils.processNonexistentUserWithValidSessionParams(request, response);
        }
    }

}