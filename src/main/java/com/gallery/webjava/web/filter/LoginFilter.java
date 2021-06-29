package com.gallery.webjava.web.filter;


import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * checking if user in system
 */
public class LoginFilter implements Filter {
    private static final Logger log = Logger.getLogger(LoginFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("LoginFilter starts working");
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        if (session.getAttribute("user") == null) {
            resp.sendRedirect("/gallery/login-page");
            log.error("user is not in system. Made redirect to login-page");
        } else {
            log.info("LoginFilter finish work");
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }


}
