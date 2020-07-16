package com.cas.commands.patient;

import com.cas.interfaces.Controller;
import com.cas.interfaces.Command;
import com.cas.dao.AppointmentDAO;
import com.cas.dao.PatientDAO;
import com.cas.entities.Appointment;
import com.cas.entities.Patient;
import com.cas.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@Controller(path = "/patient/appointments",
        description = "Return page with appointments.")
public class GetAppointments implements Command {

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
