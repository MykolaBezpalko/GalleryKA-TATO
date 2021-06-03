package com.gallery.webjava.web;

import com.gallery.webjava.db.UserDAO;
import com.gallery.webjava.db.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


//new-user
@WebServlet("/new-user")
public class CreateAccount extends HttpServlet {
    PrintWriter writer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO uDao = new UserDAO();
        HttpSession session = req.getSession();
        writer = resp.getWriter();

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repeat = req.getParameter("repeat-password");

        if (!password.equals(repeat)) {
            writer.println("<html>" +
                    "<body style=\"text-align:center;\">" +
                    "<h1 style=\" text-align: center\" > PASSWORD and REPEAT PASSWORD should be the same</h1>" +
                    "<a href=\"http://localhost:8080/gallery/create-account\"> Try Again" +
                    "</body>" +
                    "</html>");
            return;
        }
        if (LoginServlet.checkUser(email)) {
            writer.println("<html>" +
                    "<body style=\"text-align:center;\">" +
                    "<h1 style=\" text-align: center\" > User with this e-mail already exist.</h1>" +
                    "<a href=\"http://localhost:8080/gallery/create-account\"> Try Again </a>" +
                    "or <a href=\"http://localhost:8080/gallery/login-page?\">Register</a>" +
                    "</body>" +
                    "</html>");
            return;
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUserName("New User");
        uDao.createUser(user);
        user.setId(uDao.getUserId(user.getEmail()));
        session.setAttribute("user", user);
        resp.sendRedirect("./pages/user-pages/user-cabinet.html");
        System.out.println("===>> created user:");
        System.out.println(session.getAttribute("user"));

    }
}
