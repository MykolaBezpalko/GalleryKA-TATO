package com.gallery.webjava.web.filter;

import com.gallery.webjava.db.AdminDAO;
import com.gallery.webjava.db.UserDAO;
import com.gallery.webjava.db.entity.User;
import com.sun.deploy.net.HttpRequest;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = {"/buy"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        if(session.getAttribute("user") == null){
            resp.sendRedirect("/gallery/login-page");
        }else
        filterChain.doFilter(servletRequest,servletResponse);
    }


}
