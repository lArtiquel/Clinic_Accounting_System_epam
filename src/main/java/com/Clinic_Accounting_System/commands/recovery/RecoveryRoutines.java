package com.Clinic_Accounting_System.commands.recovery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class RecoveryRoutines {

    private static final String RECOVERY_SECURITY_ATTRIB = "pass_recovery_user_id";

    public static boolean checkForgotPageUsernameAttribInSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        Long id = (Long)session.getAttribute(RECOVERY_SECURITY_ATTRIB);
        return id != null;
    }

    public static void removeForgotPageAttributeFromSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute(RECOVERY_SECURITY_ATTRIB);
    }

}
