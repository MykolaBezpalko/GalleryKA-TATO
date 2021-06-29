package com.gallery.webjava.web;

import com.gallery.webjava.db.AdminDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user-cabinet/buy-ticket-pack")
public class BuyTicketsPack extends HttpServlet {
    HttpSession session;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();
        PrintWriter writer = resp.getWriter();
        List<String> choisenExpo = new ArrayList<>();
        String [] reqExpos = req.getParameterValues("EXPO");
        if(reqExpos == null) {
            writer.println("<html>" +
                    "<body style=\"text-align:center;\">" +
                    "<h1 style=\" text-align: center\" > Please, choose one of expositions </h1><br>" +
                    "<a href=\"http://localhost:8080/gallery\"> Back to homepage. </a> <br>" +
                    "<a href=\"http://localhost:8080/gallery/user-cabinet\"> Go to cabinet. </a>" +
                    "</body>" +
                    "</html>");
            return;
        }
        for(String s : reqExpos){
            choisenExpo.add(s);
        }
        session.setAttribute("choisenExpo", choisenExpo);
        resp.sendRedirect("../pay-form");
    }
}
