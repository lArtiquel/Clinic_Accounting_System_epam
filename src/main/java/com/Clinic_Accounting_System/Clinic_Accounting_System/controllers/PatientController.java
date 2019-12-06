package com.Clinic_Accounting_System.Clinic_Accounting_System.controllers;


import com.Clinic_Accounting_System.Clinic_Accounting_System.models.Events;
import com.Clinic_Accounting_System.Clinic_Accounting_System.models.Roles;
import com.Clinic_Accounting_System.Clinic_Accounting_System.models.UserInfo;
import com.Clinic_Accounting_System.Clinic_Accounting_System.repositories.DoctorsRepository;
import com.Clinic_Accounting_System.Clinic_Accounting_System.repositories.EventsRepository;
import com.Clinic_Accounting_System.Clinic_Accounting_System.repositories.UserInfoRepository;
import com.Clinic_Accounting_System.Clinic_Accounting_System.utils.AppLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private UserInfoRepository patientInfoService;

    @Autowired
    private DoctorsRepository doctorsService;

    @Autowired
    private EventsRepository eventsService;


    @GetMapping (value = "/")
    public String redirectToHomePage() {
        return "redirect:/patient/home";
    }

    @GetMapping (value = "/home")
    public String showHomePage(HttpServletRequest request){
        if(checkPatientAuth(request)){
            // making some calls to db to fetch necessary info
            HttpSession session = request.getSession();
            // look for name to greet user
            Optional<UserInfo> patientInfo = patientInfoService.findById((Long)session.getAttribute("user_id"));
            if(patientInfo.isPresent()){
                // setting firstname as request attribute to loose it after redirection
                request.setAttribute("user", patientInfo.get().getFirstName());
            } else {
                // invalidate this session and notify that user with such 'user_id' don't exist
                session.invalidate();
                session = request.getSession(true);
                session.setAttribute("message", "User with such user_id not found. Sorry!");
            }
            // search events for patient
            ArrayList<Events> events = eventsService.findAllByOnlyForPersonal(false);
            request.setAttribute("events", events);

            return "patient/home";
        } else {
            return "redirect:/sign_in";
        }
    }

    @GetMapping (value = "/account")
    public String showAccountPage(HttpServletRequest request){
        if(checkPatientAuth(request)){

            return "patient/account";
        } else {
            return "redirect:/sign_in";
        }
    }

    @GetMapping (value = "/doctors")
    public String showDoctorsPage(HttpServletRequest request){
        if(checkPatientAuth(request)){

            return "patient/doctors";
        } else {
            return "redirect:/sign_in";
        }
    }

    @GetMapping (value = "/appointments")
    public String showAppointmentsPage(HttpServletRequest request){
        if(checkPatientAuth(request)){

            return "patient/appointments";
        } else {
            return "redirect:/sign_in";
        }
    }


    private boolean checkPatientAuth(HttpServletRequest request){
        /*
            Checking session and Auth attributes for existence,
             also check if user have corresponding role to display this pack of pages.
             If he is not --> go to sign_in controller, it will handle you properly;)
         */
        HttpSession session = request.getSession(false);
        if(session != null) {
            Long id = (Long)session.getAttribute("user_id");
            Roles role = (Roles)session.getAttribute("role");
            if(id != null && role != null){
                if(role == Roles.user){
                    // updating session interval
                    session.setMaxInactiveInterval(30*60);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
