package com.cas.filters;

import com.cas.servlets.DispatcherServlet;
import com.cas.utils.ControllerUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        urlPatterns = {"/app/admin/*",
                        "/app/doctor/*",
                        "/app/patient/*",
                        "/app/logout"},
        dispatcherTypes = {DispatcherType.FORWARD},
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
        System.out.println("authentication filter");
        // check session obj existence
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        HttpSession session = httpRequest.getSession(false);

        if(session != null) {

            // fetch necessary session objects
            final Long id = (Long) session.getAttribute("user_id");
            final String role = (String) session.getAttribute("role");

            // check if role and id attributes in session
            if (id != null && role != null) {
                // means that we are authenticated
                // update max inactive interval in "no remember-me" type of session
                if(session.getMaxInactiveInterval() > 0) session.setMaxInactiveInterval(30*60);

                // get path without protocol, domain, host and context
                final String path = ControllerUtils.fetchPath(httpRequest); // example: /app/admin/home

                // if path starts with SERVLET_PREFIX then it's processed to the Front Controller;
                if(path.startsWith(DispatcherServlet.SERVLET_PREFIX)){
                    // fetch pure servlet's path without prefix
                    final String purePath = path.substring(DispatcherServlet.SERVLET_PREFIX.length());

                    /*
                        Check if user has privileges for that path.
                        I.e patient can't get admin's page and vice versa.
                    */
                    if(purePath.startsWith("/" + role) || purePath.startsWith("/logout")) {
                        filterChain.doFilter(request, response);
                        return;
                    } else {
                        // if user get lost -> redirect him to his home page
                        httpResponse.sendRedirect(httpRequest.getContextPath() +
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
        httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
    }

    @Override
    public void destroy() {}
}
