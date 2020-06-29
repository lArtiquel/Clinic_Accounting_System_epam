package com.cas.commands.doctor;

import com.cas.interfaces.Controller;
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

@Controller(path = "/doctor/MakeAnAppointment",
        description = "Make an appointment and redirect back to doctors page.")
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