package com.gallery.webjava.web.filter;

import com.gallery.webjava.db.AdminDAO;
import com.gallery.webjava.db.UserDAO;
import com.gallery.webjava.db.entity.User;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = {"/index.jsp"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        System.out.println(session.getAttribute("user") + "<=== current user");

        filterChain.doFilter(servletRequest,servletResponse);
    }


}
