package com.gallery.webjava.web;

import com.gallery.webjava.db.AdminDAO;
import com.gallery.webjava.db.DBManager;
import com.gallery.webjava.db.UserDAO;
import com.gallery.webjava.db.entity.Exposition;
import com.gallery.webjava.db.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/buy")
public class BuyTicket extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        HttpSession session = req.getSession();
        UserDAO udao = new UserDAO(DBManager.getInstance());
        List<String> chosenExpo = (List<String>) session.getAttribute("choisenExpo");
        User user = udao.getUserByEmail((String) session.getAttribute("email"));
        for (String s : chosenExpo) {
            Exposition exposition = new AdminDAO(DBManager.getInstance()).getExpositionByName(s);
            udao.createTicket(user, exposition);
        }

        pw.println("<html>" +
                "<body style=\"text-align:center;\">" +
                "<h1 style=\" text-align: center\" > Success. </h1><br>" +
                "<a href=\"http://localhost:8080/gallery\"> Back to homepage. </a> <br>" +
                "<a href=\"http://localhost:8080/gallery/user-cabinet\"> Go to cabinet. </a>" +
                "</body>" +
                "</html>");
    }
}
