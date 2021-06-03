package com.gallery.webjava.web;

import com.gallery.webjava.db.AdminDAO;
import com.gallery.webjava.db.entity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/create-event")
public class CreateExpo extends HttpServlet {
    PrintWriter pw;
    HttpSession session;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminDAO admin = new AdminDAO();
        session = req.getSession();
        pw = resp.getWriter();
        Exposition expo = new Exposition();
        try {
            String theme = req.getParameter("theme");
            java.sql.Date begin = java.sql.Date.valueOf(session.getAttribute("begin").toString());
            java.sql.Date end = java.sql.Date.valueOf(session.getAttribute("end").toString());
            Integer price = Integer.valueOf(req.getParameter("price"));
            String[] hall = (String[]) session.getAttribute("halls");
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
        resp.sendRedirect("http://localhost:8080/gallery/admin/admin-cabinet");
    }
}
