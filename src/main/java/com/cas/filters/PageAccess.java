package com.cas.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = "/pages/*",
        dispatcherTypes = {DispatcherType.FORWARD},
        filterName = "DirectPageAccessProtection",
        description = "Make JSP pages accessible only thru forwards. I.e only thru servlets.")
public class PageAccess implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}

}
