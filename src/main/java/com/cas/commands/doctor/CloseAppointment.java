package com.cas.commands.doctor;

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

public class CloseAppointment implements Command {

    private final PatientDAO patientDAO = PatientDAO.getInstance();
    private final DoctorDAO doctorDAO = DoctorDAO.getInstance();
    private final AppointmentDAO appointmentDAO = AppointmentDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // scrap params from post request scope and session
        Long doctorID = (Long)request.getSession().getAttribute("user_id");
        Long patientID = Long.parseLong(request.getParameter("patientID"));
        Date date = java.sql.Date.valueOf(request.getParameter("date"));
        int numberInQueue = Integer.parseInt(request.getParameter("numberInQueue"));
        String note = request.getParameter("note");

        // make call to database to check if patient and doctor with such ids still existing there
        Patient patient = patientDAO.getPatientById(patientID);
        Doctor doctor = doctorDAO.getDoctorById(doctorID);

        if(patient != null && doctor != null){
            // delete Appointment instance by Appointment PK fields
            appointmentDAO.removeAppointmentByPK(doctor.getId(), patient.getId(), numberInQueue, date);
            // adding note to client's medical history if note exists
            if(!note.equals("")){
                patient.setMedicalHistory(patient.getMedicalHistory() +
                        "\n-------------------New note-----------------------\n" +
                        note +
                        "\n--------------------------------------------------\n");
                patientDAO.updatePatientMedicalHistoryById(patient.getId(), patient.getMedicalHistory());
            }
            // give ticket to message about successful deleting and updating patient's information
            ControllerUtils.giveTicketToMyMessage(request, "Appointment successfully closed!");
        } else {
            ControllerUtils.giveTicketToMyMessage(request, "Patient and/or doctor do not exist anymore!");
        }

        return "redirect:/doctor/appointments";
    }

}