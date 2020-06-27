package com.Clinic_Accounting_System.filters;

import com.Clinic_Accounting_System.utils.ControllerUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        urlPatterns = {"/admin/*",
                        "/doctor/*",
                        "/patient/*",
                        "/auth/ProcessLogoutForm"},
        filterName = "AuthenticatedRoutesFilter",
        description = "Prevents from accidental access to authenticated routes when not authenticated."
)
public class AuthenticatedRoutes implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {

        // check session obj existence
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpSession session = httpRequest.getSession(false);

        if(session != null) {

            // if session objects existing
            final Long id = (Long) session.getAttribute("user_id");
            final String role = (String) session.getAttribute("role");

            // check if role and id attributes in session
            if (id != null && role != null) {
                // means that we are authenticated
                // update max inactive interval in "no remember-me" type of session
                if(session.getMaxInactiveInterval() > 0) session.setMaxInactiveInterval(30*60);

                // get path without protocol, domain, host and context
                final String path = ControllerUtils.fetchPath(httpRequest); // looks like: /context/admin/home

                // if path starts from /app/ then it's directed to the Front Controller;
                if(path.startsWith("/app/")){
                    // skip /app/ prefix and split the rest of the path into chunks
                    final String[] pathChunks = path.substring("/app/".length()).split("/");

                    /*
                        Check if user has privileges for that path.
                        I.e patient can't get admin's page.
                    */
                    if(pathChunks[0].equals(role) || pathChunks[0].equals("auth")) {
                        filterChain.doFilter(request, response);
                        return;
                    } else {
                        // if user get lost -> redirect him to his home page
                        ((HttpServletResponse)response).sendRedirect(((HttpServletRequest) request).getContextPath() +
                                "/" + role + "/home");
                        return;
                    }
                } else {
                    // not going thru Front Controller -> passing over
                    filterChain.doFilter(request, response);
                    return;
                }
            }
        }
        // we are not authenticated -> go sign in
        ((HttpServletResponse)response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/login");
    }

    @Override
    public void destroy() {}
}
