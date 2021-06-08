package com.gallery.webjava.web.pagination;

import com.gallery.webjava.db.AdminDAO;
import com.gallery.webjava.db.entity.Exposition;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import java.sql.Date;
import java.util.*;

@WebServlet("/page")
public class Pagination extends HttpServlet {
    AdminDAO admin = new AdminDAO();
    HttpSession session;
    Date today = new Date(new java.util.Date().getTime());

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();
        if(session.getAttribute("allExpo")==null){
            resp.sendRedirect("/gallery/datesorting?number=1&sortType=dateFromBegin");
            return;
        }
        List<Exposition> allExpo = (List<Exposition>) session.getAttribute("allExpo");
        pagination(req, allExpo);
        resp.sendRedirect("/gallery");
    }
}
