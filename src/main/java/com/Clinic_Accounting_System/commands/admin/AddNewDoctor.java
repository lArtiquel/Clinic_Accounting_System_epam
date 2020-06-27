package com.Clinic_Accounting_System.commands.admin;

import com.Clinic_Accounting_System.interfaces.Command;
import com.Clinic_Accounting_System.dao.DoctorDAO;
import com.Clinic_Accounting_System.dao.PatientDAO;
import com.Clinic_Accounting_System.dao.StaffEntityDAO;
import com.Clinic_Accounting_System.dao.UserDAO;
import com.Clinic_Accounting_System.entities.User;
import com.Clinic_Accounting_System.utils.ControllerUtils;
import com.Clinic_Accounting_System.utils.Roles;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;

@Log4j2
public class AddNewDoctor implements Command {

    private final UserDAO userDAO = UserDAO.getInstance();
    private final PatientDAO patientDAO = PatientDAO.getInstance();
    private final StaffEntityDAO staffEntityDAO = StaffEntityDAO.getInstance();
    private final DoctorDAO doctorDAO = DoctorDAO.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws SQLException {
        // scrap  params from request
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        final String firstname = request.getParameter("firstname");
        final String midname = request.getParameter("midname");
        final String lastname = request.getParameter("lastname");
        final Date dob = java.sql.Date.valueOf(request.getParameter("dob"));
        ControllerUtils.makeCorrectionForTimeZone(dob);
        final String phone = request.getParameter("phone");
        final String email = request.getParameter("email");
        final String address = request.getParameter("address");
        final String degree = request.getParameter("degree");
        final String specialization = request.getParameter("specialization");
        Double salary = 0.0;
        try {
            salary = Double.parseDouble(request.getParameter("salary"));
        } catch (NullPointerException | NumberFormatException e){
            ControllerUtils.giveTicketToMyMessage(request, "Wrong `salary` field!");
            log.error("Error parsing `salary` parameter. Class: " + this.getClass().getName() +
                    "\nMessage: " + e.getMessage());
            return "redirect:/admin/doctors";
        }

        // persist data in database
        if(!userDAO.existsByUsername(username)) {
            userDAO.createUser(username, password, Roles.doctor.name());
            final User user = userDAO.getByUsername(username);
            patientDAO.createPatient(user.getId(), firstname, lastname, midname, dob,
                                    email, phone, address, "");
            staffEntityDAO.createStaffEntity(user.getId(), salary);
            doctorDAO.createDoctor(user.getId(), degree, specialization);
            // notify administrator about successful operation
            ControllerUtils.giveTicketToMyMessage(request, "Doctor successfully added!");
        } else {
            ControllerUtils.giveTicketToMyMessage(request, "Doctor with such username already exists!");
        }
        return "redirect:/admin/doctors";
    }
}
