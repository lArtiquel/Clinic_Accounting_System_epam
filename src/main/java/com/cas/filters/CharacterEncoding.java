package com.cas.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*",
//        filterName = "CharacterEncodingFilter",
//        description = "Sets response and request char encoding to UTF-8.")
//public class CharacterEncoding implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {}
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println("character encoding");
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {}
//}
