package com.Clinic_Accounting_System.commands.doctor;

import com.Clinic_Accounting_System.interfaces.Command;
import com.Clinic_Accounting_System.dao.EventDAO;
import com.Clinic_Accounting_System.dao.PatientDAO;
import com.Clinic_Accounting_System.entities.Event;
import com.Clinic_Accounting_System.entities.Patient;
import com.Clinic_Accounting_System.utils.ControllerUtils;
import lombok.extern.log4j.Log4j2;

import javax.servlet.annotation.WebServlet;
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
