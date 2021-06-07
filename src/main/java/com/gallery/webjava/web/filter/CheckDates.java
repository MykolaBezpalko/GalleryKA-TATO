package com.gallery.webjava.web.filter;

import com.gallery.webjava.db.DBManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Date;

import java.util.*;

@WebServlet("/admin/admin-cabinet/check-dates")
public class CheckDates extends HttpServlet {
    RequestDispatcher dispatcher;
    HttpSession session;
    PrintWriter pw;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        HashSet<java.sql.Date> dates = new HashSet<>();
        String beginParam = req.getParameter("start-date");
        String endParam = req.getParameter("end-date");
        session = req.getSession();
        pw = resp.getWriter();
        dispatcher = req.getRequestDispatcher("../admin-cabinet");
        java.util.Date begin;
        java.util.Date end;
        if ((beginParam != null || endParam != null)) {
            begin = end = new Date(new java.util.Date().getTime());
        } else {
            begin = Date.valueOf(beginParam);
            end = Date.valueOf(endParam);
        }

        String[] chosenHalls = req.getParameterValues("hall");
        StringBuilder builder = new StringBuilder();

        if (begin.after(end)) {
            pw.println("<html>" +
                    "<body style=\"text-align:center;\">" +
                    "<h1 style=\" text-align: center\" > Error: BEGIN date should be before  END date </h1><br>" +
                    "<a href=\"http://localhost:8080/gallery\"> Back to homepage. </a> <br>" +
                    "<a href=\"http://localhost:8080/gallery/admin/admin-cabinet\"> Go to cabinet. </a>" +
                    "</body>" +
                    "</html>");
            return;

        }
        session = req.getSession();
        session.setAttribute("begin", req.getParameter("start-date"));
        session.setAttribute("end", req.getParameter("end-date"));

        if (chosenHalls == null) {
            pw.println("<html>" +
                    "<body style=\"text-align:center;\">" +
                    "<h1 style=\" text-align: center\" > Error: chose halls for check available dates</h1><br>" +
                    "<a href=\"http://localhost:8080/gallery\"> Back to homepage. </a> <br>" +
                    "<a href=\"http://localhost:8080/gallery/admin/admin-cabinet\"> Go to cabinet. </a>" +
                    "</body>" +
                    "</html>");
            return;
        }

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
        builder.deleteCharAt(builder.length() - 1);
        session.setAttribute("chosenHalls", builder);
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

    private String datesArr(HashSet<java.sql.Date> d) {
        StringBuilder sb = new StringBuilder();

        for (Date dd : d) {
            sb.append('"').append(dd).append('"').append(",");
        }
        return sb.toString();
    }
}
