package com.cas.commands.patient;

import com.cas.interfaces.Command;
import com.cas.dao.AppointmentDAO;
import com.cas.dao.DoctorDAO;
import com.cas.dao.PatientDAO;
import com.cas.entities.Doctor;
import com.cas.entities.Patient;
import com.cas.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
