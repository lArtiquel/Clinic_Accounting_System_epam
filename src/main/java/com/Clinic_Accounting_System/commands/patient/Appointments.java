package com.Clinic_Accounting_System.commands.patient;

import com.Clinic_Accounting_System.interfaces.Command;
import com.Clinic_Accounting_System.dao.AppointmentDAO;
import com.Clinic_Accounting_System.dao.PatientDAO;
import com.Clinic_Accounting_System.entities.Appointment;
import com.Clinic_Accounting_System.entities.Patient;
import com.Clinic_Accounting_System.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class Appointments implements Command {

    private final PatientDAO patientDAO = PatientDAO.getInstance();
    private final AppointmentDAO appointmentDAO = AppointmentDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // fetch patient from db
        Patient patient = patientDAO.getPatientById((Long)request.getSession().getAttribute("user_id"));

        if(patient != null){
            // fetch patient's appointments from db
            List<Appointment> appointments = appointmentDAO.getAppointmentsByPatientId(patient.getId());

            // push list of apps to req scope
            request.setAttribute("appointments", appointments);

            return "forward:/pages/patient/appointments.jsp";
        } else {
            return ControllerUtils.processNonexistentUserWithValidSessionParams(request, response);
        }
    }

}
