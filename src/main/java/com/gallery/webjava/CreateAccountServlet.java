package com.gallery.webjava;

import javax.servlet.RequestDispatcher;
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
public class CreateAccountServlet extends HttpServlet {
    PrintWriter writer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        writer = resp.getWriter();
        session.setAttribute("email", req.getParameter("email"));
        session.setAttribute("password", req.getParameter("password"));
        session.setAttribute("repeat-password", req.getParameter("repeat-password"));

        String email = session.getAttribute("email").toString();
        String password = session.getAttribute("password").toString();
        String repeat = session.getAttribute("repeat-password").toString();

        if (!password.equals(repeat)) {
            writer.println("<html>" +
                    "<body >" +
                    "<h1 style=\" text-align: center\" > PASSWORD and REPEAT PASSWORD should be the same</h1>" +
                    "<a href=\"http://localhost:8080/gallery/create-account\"> Try Again" +
                    "</body>" +
                    "</html>");
            return;
        }else{
            writer.println("<html>" +
                    "<body >" +
                    "<h1 style=\" text-align: center\" > PASSWORD and REPEAT PASSWORD ARE OK </h1>" +
//                    "<a href=\"http://localhost:8080/gallery/create-account\" > Try Again" +
                    "</body>" +
                    "</html>");
        }

    }
}
