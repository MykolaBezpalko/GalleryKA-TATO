package com.gallery.webjava.web.filter;

import com.gallery.webjava.db.DBManager;
import com.gallery.webjava.db.entity.Hall;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;

import java.util.*;

@WebServlet("/admin/check-dates")
public class CheckDates extends HttpServlet {
    RequestDispatcher dispatcher;
    HttpSession session;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();
        dispatcher = req.getRequestDispatcher("admin-cabinet");
        HashSet<java.sql.Date> dates = new HashSet<>();
        java.util.Date begin = Date.valueOf(req.getParameter("start-date"));
        java.util.Date end = Date.valueOf(req.getParameter("end-date"));
        String[] chosenHalls = req.getParameterValues("hall");
        StringBuilder builder = new StringBuilder();
        if (begin.after(end)) {
            System.out.println("incorrect: begin should be after end");
            dispatcher.forward(req, resp);
            return;
        }
        session = req.getSession();
        session.setAttribute("begin", req.getParameter("start-date"));
        session.setAttribute("end", req.getParameter("end-date"));
        Connection conn = null;

        for (String s : chosenHalls) {
            builder.append(s).append(',');
            try {
                conn = DBManager.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement("select * from hall\n" +
                        "join hall_exposition on hall.id = hall_id\n" +
                        "join exposition  on hall_exposition.exposition_id = exposition.id\n" +
                        "where hall.name =?");
                ps.setString(1, s);
                ResultSet rs = ps.executeQuery();
                Date dbBegin;
                Date dbEnd;
                HashSet<java.sql.Date> dbDates;
                while (rs.next()) {
                    dbBegin = rs.getDate(7);
                    dbEnd = rs.getDate(8);
                    dbDates = period(dbBegin, dbEnd);
                    dates.addAll(dbDates);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                DBManager.getInstance().commitAndClose(conn);
            }
        }
        session.setAttribute("chosenHalls", builder );
        session.setAttribute("halls", chosenHalls);
        session.setAttribute("availableDates", datesArr(dates));
        dispatcher.forward(req, resp);
    }

    private HashSet<java.sql.Date> period(java.util.Date start, java.util.Date end) {
        HashSet<java.sql.Date> list = new HashSet<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(start);
        while (calendar.getTime().compareTo(end) < 1) {
            java.util.Date result = calendar.getTime();
            list.add(new Date(result.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
        return list;
    }

    private String  datesArr(HashSet<java.sql.Date> d) {
        StringBuilder sb = new StringBuilder();

        for(Date dd : d){
            sb.append('"').append(dd).append('"').append(",");
        }
        return sb.toString();
    }
}
