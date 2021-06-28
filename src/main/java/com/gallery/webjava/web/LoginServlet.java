package com.gallery.webjava.web;

import com.gallery.webjava.db.DBManager;
import com.gallery.webjava.db.UserDAO;
import com.gallery.webjava.db.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

//check-data
public class LoginServlet extends HttpServlet {
    RequestDispatcher dispatcher;
    HttpSession session;
    PrintWriter writer;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        writer = resp.getWriter();
        session = req.getSession();
        session.setAttribute("email", req.getParameter("email"));
        session.setAttribute("password", req.getParameter("password"));
        String email = session.getAttribute("email").toString();
        if (email == null || !checkUser(email)) {
            dispatcher = req.getRequestDispatcher("pages/info-pages/not-registered.html");
            dispatcher.forward(req, resp);
        } else {
            String password = session.getAttribute("password").toString();
            User user = new UserDAO(DBManager.getInstance()).getUser(email,password);
            session.setAttribute("user", user);
            if (user != null) {
                writer.println("<html>" +
                        "<body style=\"text-align:center;\">" +
                        "<h1 style=\" text-align: center\" > You are in system now.</h1><br>" +
                        "<a href=\"http://localhost:8080/gallery\"> Back to homepage. </a> <br>" +
                        "<a href=\"http://localhost:8080/gallery/user-cabinet\"> Go to cabinet. </a>" +
                        "</body>" +
                        "</html>");
            } else {
                writer.println("<html>" +
                        "<body style=\"text-align:center;\">" +
                        "<h1 style=\" text-align: center\" > Incorrect password.</h1><br>" +
                        "<a href=\"http://localhost:8080/gallery/login-page\"> Try Again. </a> <br>" +
                        "<a href=\"http://localhost:8080/gallery\"> Go to homepage. </a>" +
                        "</body>" +
                        "</html>");
            }
        }
    }

    public static boolean checkUser(String email) {
        User u = new UserDAO(DBManager.getInstance()).getUserByEmail(email);
        if (u != null) {
            return true;
        } else {
            return false;
        }
    }
}
