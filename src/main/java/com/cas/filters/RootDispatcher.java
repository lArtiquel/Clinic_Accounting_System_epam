package com.cas.filters;

import com.cas.utils.ControllerUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(
        urlPatterns = {"/*"},
        dispatcherTypes = {DispatcherType.REQUEST},
        filterName = "RootDispatcherFilter",
        description = "Forwards browser requests to the /app/* Front Controller if it's not a resource or page."
)
public class RootDispatcher implements Filter {

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

        if (path.startsWith("/static/")) {
            // filter only static resources
            filterChain.doFilter(request, response);
        } else {
            /*
                Assume that by default request should go thru Front Controller
                So, add `/app` prefix to the path and forward to the same route.
             */
            request.getRequestDispatcher("/app" + path).forward(request, response);
        }
    }

    @Override
    public void destroy() {}

}
