package com.Clinic_Accounting_System.commands.admin;

import com.Clinic_Accounting_System.interfaces.Command;
import com.Clinic_Accounting_System.dao.AppointmentDAO;
import com.Clinic_Accounting_System.dao.PatientDAO;
import com.Clinic_Accounting_System.entities.Patient;
import com.Clinic_Accounting_System.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DeletePatient implements Command {

    private final PatientDAO patientDAO = PatientDAO.getInstance();
    private final AppointmentDAO appointmentDAO = AppointmentDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // fetch patient from db by patientID from request
        Long patientID = Long.parseLong(request.getParameter("patientID"));

        // retrieve patient from db
        Patient patient = patientDAO.getPatientById(patientID);
        if(patient != null){
            // remove appointments of removed patient
            appointmentDAO.removeAllAppointmentsByPatientId(patientID);
            // remove from `user_info` table, method also removes patient from `users`
            patientDAO.removePatient(patientID);
            // notify about successful delete operation
            ControllerUtils.giveTicketToMyMessage(request, "Patient successfully deleted!");
        } else {
            ControllerUtils.giveTicketToMyMessage(request, "Such patient do not exist anymore!");
        }

        return "redirect:/admin/patients";
    }

}
