package com.Clinic_Accounting_System.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/pages/*",
        filterName = "DirectPageAccessProtection",
        description = "Prevents direct access to pages(not thru servlets).")
public class DirectPageAccessProtection implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        httpRequest.getRequestDispatcher("/pages/errors/404.jsp").forward(httpRequest, response);
    }

    @Override
    public void destroy() {}
}
