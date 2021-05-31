package com.gallery.webjava.web;

import com.gallery.webjava.db.AdminDAO;
import com.gallery.webjava.db.Encoder;
import com.gallery.webjava.db.UserDAO;
import com.gallery.webjava.db.entity.Administrator;
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
            User user = new UserDAO().getUserByEmail(email);
            session.setAttribute("user", user);
            if (user != null) {
                writer.println(user);
            } else {
                //change after on error page.
                writer.println("incorrect email email");
            }

        }

    }

    public static boolean checkUser(String email) {
        User u = new UserDAO().getUserByEmail(email);
        if (u != null) {
            return true;
        } else {
            return false;
        }
    }
}
