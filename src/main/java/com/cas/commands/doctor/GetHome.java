package com.cas.commands.doctor;

import com.cas.interfaces.Command;
import com.cas.dao.EventDAO;
import com.cas.dao.PatientDAO;
import com.cas.entities.Event;
import com.cas.entities.Patient;
import com.cas.utils.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class GetHome implements Command {

    private final PatientDAO patientDAO = PatientDAO.getInstance();
    private final EventDAO eventDAO = EventDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // fetch patient from db
        Patient patient = patientDAO.getPatientById((Long)request.getSession().getAttribute("user_id"));

        if(patient != null){
            // set firstname to greet user
            request.setAttribute("user", patient.getFirstname());
            // search events for doctor
            List<Event> events = eventDAO.getAll();
            request.setAttribute("events", events);
            return "forward:/pages/doctor/home.jsp";
        } else {
            return ControllerUtils.processNonexistentUserWithValidSessionParams(request, response);
        }
    }

}
