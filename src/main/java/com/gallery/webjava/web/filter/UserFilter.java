package com.gallery.webjava.web.filter;

import com.gallery.webjava.db.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * checking if current user instance of User class
 */

@WebFilter("/user-cabinet")
public class UserFilter implements Filter {
    private static final Logger log = Logger.getLogger(LoginFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("UserFilter start working");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Object u = session.getAttribute("user");
        if(!(u instanceof User)){
            response.sendRedirect("/gallery/login-page");
            log.error("Current user is not instance of User class. Redirect");
        }else{
            log.info("UserFilter finish working");
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
