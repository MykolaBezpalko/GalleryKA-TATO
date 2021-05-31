package com.gallery.webjava.web.filter;

import com.gallery.webjava.db.entity.Administrator;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/create-event")
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("adminFilter works");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        RequestDispatcher dispatcher;
        PrintWriter writer;
        boolean ifAdmin = session.getAttribute("user") instanceof Administrator;
        writer = resp.getWriter();
        if (session.getAttribute("user") == null) {
            writer.println("<html>" +
                    "<body style=\"text-align:center;\">" +
                    "<h1 style=\" text-align: center\" > You have no acces to this page.</h1>" +
                    "<a href=\"http://localhost:8080/gallery/pages/admin-pages/login-as-admin.jsp?\">Please login as administrator</a>" +
                    "<br>" +
                    "<a href=\"http://localhost:8080/gallery\">Back to homepage </a>" +
                    "</body>" +
                    "</html>");
            return;
        }
        if (!ifAdmin) {
            writer.println("<html>" +
                    "<body style=\"text-align:center;\">" +
                    "<h1 style=\" text-align: center\" > You are not Administrator</h1>" +
                    "<a href=\"http://localhost:8080/gallery\"> Back to homepage </a>" +
                    "</body>" +
                    "</html>");
            return;
        }


        filterChain.doFilter(servletRequest, servletResponse);
    }
}
