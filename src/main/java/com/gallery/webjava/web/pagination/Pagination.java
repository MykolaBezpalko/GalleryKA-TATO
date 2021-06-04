package com.gallery.webjava.web.pagination;

import com.gallery.webjava.db.AdminDAO;
import com.gallery.webjava.db.entity.Exposition;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import java.util.LinkedList;
import java.util.List;

@WebServlet("/page")
public class Pagination extends HttpServlet {
    List<Exposition> allExpo = new AdminDAO().getAllExpositions();
    HttpSession session;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       pagination(req, resp);
       resp.sendRedirect("/gallery");
    }

    private void pagination(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();
        Integer pageNumber = Integer.valueOf(req.getParameter("number"));
        int startId = (pageNumber * 3) - 3;
        List<Exposition> partExpos= new LinkedList<>();

        for( int i = startId; i<= startId+2 & i< allExpo.size(); i++ ){
            partExpos.add(allExpo.get(i));
        }
        session.setAttribute("expos", partExpos);

    }
}
