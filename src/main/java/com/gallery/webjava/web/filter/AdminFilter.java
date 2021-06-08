package com.gallery.webjava.web.filter;

import com.gallery.webjava.db.DBManager;
import com.gallery.webjava.db.entity.Administrator;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Checking if current user is Administrator
 */
public class AdminFilter implements Filter {
    private static final Logger log = Logger.getLogger(AdminFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("AdminFilter filter starts work");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        PrintWriter  writer = resp.getWriter();

        boolean ifAdmin = session.getAttribute("user") instanceof Administrator;

        if (session.getAttribute("user") == null) {
            writer.println("<html>" +
                    "<body style=\"text-align:center;\">" +
                    "<h1 style=\" text-align: center\" > You have no acces to this page.</h1>" +
                    "<a href=\"http://localhost:8080/gallery/pages/admin-pages/login-as-admin.jsp?\">Please login as administrator</a>" +
                    "<br>" +
                    "<a href=\"http://localhost:8080/gallery\">Back to homepage </a>" +
                    "</body>" +
                    "</html>");
            log.error("Current user is not in system");
            return;
        }
        if (!ifAdmin) {
            writer.println("<html>" +
                    "<body style=\"text-align:center;\">" +
                    "<h1 style=\" text-align: center\" > You are not Administrator</h1>" +
                    "<a href=\"http://localhost:8080/gallery\"> Back to homepage </a>" +
                    "</body>" +
                    "</html>");
            log.error("Current user is not Administrator");
            return;
        }

        log.info("AdminFilter not find any errors.");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
