package com.gallery.webjava.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


//зробити недоступними папки юзера і адміна з jsp!!!!
public class JSPFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        PrintWriter writer = response.getWriter();
        writer.println(
                "<html>" +
                "<body style=\" text-align: center; padding-top: 100px;\">" +
                "<h1> You have not access here.</h1>" +
                "<h3> " +
                "<a href=\"http://localhost:8080/gallery/\"> Go to Homepage</a>" +
                "</h3>" +
                "</body>" +
                "</html>");
    }
}