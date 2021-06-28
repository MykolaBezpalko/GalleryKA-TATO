package com.gallery.webjava.web;

import com.gallery.webjava.db.AdminDAO;
import com.gallery.webjava.db.DBManager;
import com.gallery.webjava.db.UserDAO;
import com.gallery.webjava.db.entity.*;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/create-event")
public class CreateExpo extends HttpServlet {
    private static final Logger log = Logger.getLogger(CreateExpo.class);
    PrintWriter pw;
    HttpSession session;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminDAO admin = new AdminDAO(DBManager.getInstance());
        session = req.getSession();
        pw = resp.getWriter();
        Exposition expo = new Exposition();
        try {
            String beginDate = session.getAttribute("begin").toString();
            String endDate = session.getAttribute("end").toString();
            String theme = req.getParameter("theme");
            Integer price = Integer.valueOf(req.getParameter("price"));
            String[] hall = (String[]) session.getAttribute("halls");
            String description_ua = req.getParameter("description-ua");
            String description_en = req.getParameter("description-en");

            //handle thrown IllegalArgumentException while null
            java.sql.Date begin = java.sql.Date.valueOf(beginDate);
            java.sql.Date end = java.sql.Date.valueOf(endDate);


            for (String s : hall) {
                Hall h = admin.getHallByName(s);
                expo.addHall(h);
            }


            expo.setTheme(theme);
            expo.setBegin(begin);
            expo.setEnd(end);
            expo.setPrice(price);

            admin.createExposition(expo);

            expo.setId(admin.getExpoId(expo.getTheme()));

            admin.settHallsForExpo(expo, expo.getHalls());

            admin.setDescriptions(expo, description_en, 1);
            admin.setDescriptions(expo, description_ua, 2);

        } catch (IllegalStateException | NumberFormatException e) {
            log.error("Cant create expo from admin cabinet");
        }
        resp.sendRedirect("http://localhost:8080/gallery/admin/admin-cabinet");
    }
}
