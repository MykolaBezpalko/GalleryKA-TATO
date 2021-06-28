package com.gallery.webjava.web.tag;

import com.gallery.webjava.db.DBManager;
import com.gallery.webjava.db.Manager;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaginationTag extends TagSupport {
    static final Logger log = Logger.getLogger(TicketsCountTag.class);
    double pages;
    int recordPerPage = 3;
    String sorting;

    public void setSorting(String sorting) {
        this.sorting = sorting;
    }

    Manager dbManager = DBManager.getInstance();

    public int getRecordsCount() {
        int rc = 0;
        Connection conn = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            conn = dbManager.getConnection();
            ps = conn.prepareStatement("SELECT count(*) from exposition where end_date > now() ");
            rs = ps.executeQuery();
            while (rs.next()) {
                rc = rs.getInt(1);
            }

        } catch (SQLException e) {
            log.error(e);
        } finally {
            dbManager.commitAndClose(conn);
        }
        return rc;
    }

    @Override
    public int doStartTag() {

        JspWriter writer = pageContext.getOut();
        pages = getRecordsCount() / (double) recordPerPage;
        pages = Math.ceil(pages);
        int p = (int) pages;

        try {
            writer.println("<form action=\"sorting\"" +
                    "<nav aria-label=\"...\"><ul class=\"pagination\">");
            for (int i = 1; i <= p; i++) {
                writer.println("<li class=\"page-item\"><a class=\"page-link\" href=\"/gallery/page?number=" + i + "\">" + i + "</a></li>");
            }
            writer.println("</ul></nav><form>");

        } catch (IOException e) {
            log.error(e);
        }

        return SKIP_BODY;
    }

}
