package com.cas.commands;

import com.cas.interfaces.Command;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    // singleton immutable map with all registered routes and its corresponding commands
    private static final Map<String, Command> COMMANDS = new HashMap<String, Command>() {{
        // admin's commands
        // Get requests
        put("/admin/home", new com.cas.commands.admin.GetHome());
        put("/admin/doctors", new com.cas.commands.admin.GetDoctors());
        put("/admin/patients", new com.cas.commands.admin.GetPatients());
        put("/admin/events", new com.cas.commands.admin.GetEvents());
        put("/admin/appointments", new com.cas.commands.admin.GetAppointments());
        // Post requests
        put("/admin/AddNewEvent", new com.cas.commands.admin.AddNewEvent());
        put("/admin/DeleteEvent", new com.cas.commands.admin.DeleteEvent());
        put("/admin/AddNewDoctor", new com.cas.commands.admin.AddNewDoctor());
        put("/admin/DeleteDoctor", new com.cas.commands.admin.DeleteDoctor());
        put("/admin/DeletePatient", new com.cas.commands.admin.DeletePatient());
        put("/admin/EditPatient", new com.cas.commands.admin.EditPatient());
        put("/admin/DeleteAppointmentsAfterDate", new com.cas.commands.admin.DeleteAppointmentsAfterDate());

        // doctor's commands
        // Get requests
        put("/doctor/home", new com.cas.commands.doctor.GetHome());
        put("/doctor/account", new com.cas.commands.doctor.GetAccount());
        put("/doctor/profile", new com.cas.commands.doctor.GetProfile());
        put("/doctor/patients", new com.cas.commands.doctor.GetPatients());
        put("/doctor/doctors", new com.cas.commands.doctor.GetDoctors());
        put("/doctor/appointments", new com.cas.commands.doctor.GetAppointments());
        // Post requests
        put("/doctor/AddNewEvent", new com.cas.commands.doctor.AddNewEvent());
        put("/doctor/EditCredentials", new com.cas.commands.doctor.EditCredentials());
        put("/doctor/EditProfile", new com.cas.commands.doctor.EditProfile());
        put("/doctor/EditPatientsMedicalHistory", new com.cas.commands.doctor.EditPatientsMedicalHistory());
        put("/doctor/MakeAnAppointment", new com.cas.commands.doctor.MakeAnAppointment());
        put("/doctor/CloseAppointment", new com.cas.commands.doctor.CloseAppointment());

        // patient's commands
        // Get requests
        put("/patient/home", new com.cas.commands.patient.GetHome());
        put("/patient/account", new com.cas.commands.patient.GetAccount());
        put("/patient/profile", new com.cas.commands.patient.GetProfile());
        put("/patient/doctors", new com.cas.commands.patient.GetDoctors());
        put("/patient/appointments", new com.cas.commands.patient.GetAppointments());
        // Post requests
        put("/patient/EditCredentials", new com.cas.commands.patient.EditCredentials());
        put("/patient/EditProfile", new com.cas.commands.patient.EditProfile());
        put("/patient/MakeAnAppointment", new com.cas.commands.patient.MakeAnAppointment());

        // recovery commands
        put("/recovery/enter-username", new com.cas.commands.recovery.GetEnterUsernamePage());
        put("/recovery/enter-code", new com.cas.commands.recovery.GetEnterCodePage());
        put("/recovery/ProcessEnterUsernameForm", new com.cas.commands.recovery.ProcessEnterUsernameForm());
        put("/recovery/ProcessEnterCodeForm", new com.cas.commands.recovery.ProcessEnterCodeForm());

        // registration commands
        put("/registration", new com.cas.commands.registration.GetRegistrationPage());
        put("/registration/ProcessRegistrationForm", new com.cas.commands.registration.ProcessRegistrationForm());

        // auth commands
        put("/login", new com.cas.commands.auth.GetLoginPage());
        put("/ProcessLoginForm", new com.cas.commands.auth.ProcessLoginForm());
        put("/logout", new com.cas.commands.auth.ProcessLogoutForm());
    }};

    public static Command getCommand(String path) {
        // go on login page when path is /
        if(path.equals("/")) path = "/login" ;
        // returns specified command for that path, null if map contains no mapping for that key.
        return COMMANDS.get(path);
    }

}
