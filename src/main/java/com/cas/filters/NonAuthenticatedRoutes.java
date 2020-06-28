package com.cas.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        urlPatterns = {"/app/login",
                        "/app/ProcessLoginForm",
                        "/app/recovery/*",
                        "/app/registration/*"},
        dispatcherTypes = {DispatcherType.FORWARD},
        filterName = "NonAuthenticatedRoutesFilter",
        description = "Prevents from accidental access to non-authenticated routes when authenticated."
)
public class NonAuthenticatedRoutes implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("non-auth filter");
        // fetch session from request
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        HttpSession session = httpRequest.getSession(false);

        // check session on existence
        if(session != null) {
            // check session objects on existence
            Long id = (Long) session.getAttribute("user_id");
            String role = (String) session.getAttribute("role");

            if (id != null && role != null) {
                // means that we are authenticated -> redirect to home page
                httpResponse.sendRedirect(httpRequest.getContextPath() +
                                            "/" + role + "/home");
                return;
            }
        }
        // if not authenticated -> pass over
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
