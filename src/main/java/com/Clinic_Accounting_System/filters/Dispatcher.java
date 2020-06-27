package com.Clinic_Accounting_System.filters;

import com.Clinic_Accounting_System.utils.ControllerUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        urlPatterns = {"/*"},
        filterName = "DispatcherFilter",
        description = "Forwards browser requests to the /app/* Front Controller if it's not a resource or page."
)
public class Dispatcher implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {
        // cast Servlet Response to HttpServletResponse
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // get path, ie www.art.com/hello/world, path info is /hello/world if contextPath www.art.com
        final String path = ControllerUtils.fetchPath(httpRequest);

        if (path.startsWith("/static/") || path.startsWith("/pages/")) {
            // avoid Front Controller
            filterChain.doFilter(request, response);
        } else {
            // go thru Front Controller
            request.getRequestDispatcher("/app" + path).forward(request, response);
        }
    }

    @Override
    public void destroy() {}

}
