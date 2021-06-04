package com.gallery.webjava.web.filter;

import com.gallery.webjava.db.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/user-cabinet")
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        System.out.println("user filter works");
        Object u = session.getAttribute("user");
        if(!(u instanceof User)){
            response.sendRedirect("/gallery/login-page");
        }else

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
