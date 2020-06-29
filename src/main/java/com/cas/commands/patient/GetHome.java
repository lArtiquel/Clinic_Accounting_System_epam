package com.cas.commands.patient;

import com.cas.interfaces.Controller;
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

@Controller(path = "/patient/home",
        description = "Return home page with events marked with !onlyForPersonal flag.")
public class GetHome implements Command {

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