package com.gallery.webjava.web;

import com.gallery.webjava.db.AdminDAO;

import static com.gallery.webjava.db.Constants.*;

import com.gallery.webjava.db.entity.Exposition;
import com.gallery.webjava.db.entity.Hall;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/create-event")
public class CreateExpo extends HttpServlet {
    PrintWriter pw;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminDAO admin = new AdminDAO();
        pw = resp.getWriter();
        Exposition expo = new Exposition();
        try {
            String theme = req.getParameter("theme");
            java.sql.Date begin = java.sql.Date.valueOf(req.getParameter("start-date"));
            java.sql.Date end = java.sql.Date.valueOf(req.getParameter("end-date"));
            Integer price = Integer.valueOf(req.getParameter("price"));
            String[] hall = req.getParameterValues("hall");
            String description_ua = req.getParameter("description-ua");
            String description_en = req.getParameter("description-en");

            for (String s : hall) {
                Hall h = admin.getHallByName(s);
                expo.addHall(h);
            }

            if (theme == null || price == null || hall == null) {
                System.err.println("One of fields empty");
                throw new IllegalStateException();
            }
            expo.setTheme(theme);
            expo.setBegin(begin);
            expo.setEnd(end);
            expo.setPrice(price);

            admin.createExposition(expo);

            expo.setId(admin.getExpoId(expo.getTheme()));

            admin.settHallsForExpo(expo, expo.getHalls());

            admin.setDescriptions(expo, description_en,1);
            admin.setDescriptions(expo, description_ua,2);

        } catch (IllegalStateException | NumberFormatException e ) {
            System.out.println("Cant create expo from admin cabinet");
            e.printStackTrace();
        }
        pw.println("<html>" +
                "<h1>" +
                expo + "</h1>" +
                "</html>");
    }
}
