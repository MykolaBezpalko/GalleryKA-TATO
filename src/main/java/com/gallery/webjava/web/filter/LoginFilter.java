package com.gallery.webjava.web.filter;

import com.gallery.webjava.db.AdminDAO;
import com.gallery.webjava.db.UserDAO;
import com.gallery.webjava.db.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = {"/login"})
public class LoginFilter implements Filter {
    static AdminDAO admin = new AdminDAO();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        RequestDispatcher dispatcher;
        String email = servletRequest.getParameter("email");
        String password = servletRequest.getParameter("password");
        String repeat = servletRequest.getParameter("repeat-password");
        PrintWriter writer = servletResponse.getWriter();
        if(!checkUser(email)){
            User newUser = new User();
            newUser.setEmail(email);
            if(!password.equals(repeat)){
//                dispatcher = servletRequest.getRequestDispatcher("https://www.google.com");
//                dispatcher.forward(servletRequest,servletResponse);
                writer.print("<html>" +
                        "<head> <link rel=\"stylesheet\" href=\"styles/style.css\"></head>" +
                        "<body>" +
                        "<h1>Паролі не співпадають, спробуйте ще.</h1>" +
                        "</body>" +
                        "</html>");
                return;
            }
            else
                newUser.setPassword(password);
            new UserDAO().createUser(newUser);
        }else {
            dispatcher = servletRequest.getRequestDispatcher("/login");
            dispatcher.forward(servletRequest,servletResponse);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    public boolean checkUser(String email){
        Object u = admin.getAdminByEmail(email);
        if(u != null){
            return true;
        }
        else{
           u = new UserDAO().getUserByEmail(email);
           if(u != null)
            return true;
        }
        return false;
    }
}
