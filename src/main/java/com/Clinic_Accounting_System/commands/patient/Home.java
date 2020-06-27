package com.Clinic_Accounting_System.commands.patient;

import com.Clinic_Accounting_System.interfaces.Command;
import com.Clinic_Accounting_System.dao.EventDAO;
import com.Clinic_Accounting_System.dao.PatientDAO;
import com.Clinic_Accounting_System.entities.Event;
import com.Clinic_Accounting_System.entities.Patient;
import com.Clinic_Accounting_System.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class Home implements Command {

    private final PatientDAO patientDAO = PatientDAO.getInstance();
    private final EventDAO eventDAO = EventDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // fetch patient's info
        final Patient patient = patientDAO.getPatientById((Long)request.getSession().getAttribute("user_id"));

        if(patient != null){
            // user attrib to greet user by first name
            request.setAttribute("user", patient.getFirstname());
            // fetch patient's events
            List<Event> events = eventDAO.getAllEventsByOnlyForPersonal(false);
            request.setAttribute("events", events);
            return "forward:/pages/patient/home.jsp";
        } else {
            return ControllerUtils.processNonexistentUserWithValidSessionParams(request, response);
        }
    }

}