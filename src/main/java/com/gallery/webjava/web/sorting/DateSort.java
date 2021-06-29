package com.gallery.webjava.web.sorting;


import com.gallery.webjava.db.AdminDAO;
import com.gallery.webjava.db.DBManager;
import com.gallery.webjava.db.entity.Exposition;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/datesorting")
public class DateSort extends HttpServlet {
    AdminDAO admin = new AdminDAO(DBManager.getInstance());
    HttpSession session;
    Date today = new Date(new java.util.Date().getTime());
    String typeSort;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Exposition> allExpo = null;
        session = req.getSession();
        typeSort = req.getParameter("sortType");
        switch (typeSort) {
            case "dateFromBegin":
                allExpo = admin.sortedExpoByTimeAsc();
                break;
            case "dateFromEnd":
                allExpo = admin.sortedExpoByTimeDesc();
                break;
        }
        session.setAttribute("allExpo", allExpo);
        pagination(req, allExpo);
        resp.sendRedirect("/gallery");
    }

    private void pagination(HttpServletRequest req, List<Exposition> expo) {
        expo.removeIf(iterExp -> iterExp.getEnd().before(today));
        session = req.getSession();
        int pageNumber = Integer.parseInt(req.getParameter("number"));
        int startId = (pageNumber * 3) - 3;
        List<Exposition> partExpos = new LinkedList<>();
        for (int i = startId; i <= startId + 2 & i < expo.size(); i++) {
            partExpos.add(expo.get(i));
        }
        session.setAttribute("expos", partExpos);
    }
}
