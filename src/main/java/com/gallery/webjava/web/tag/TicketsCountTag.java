package com.gallery.webjava.web.tag;

import com.gallery.webjava.db.AdminDAO;
import com.gallery.webjava.db.DBManager;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;



public class TicketsCountTag extends TagSupport {
    static final Logger log = Logger.getLogger(TicketsCountTag.class);
    Integer expoId;

    public void setExpoId(Integer expoId) {
        this.expoId = expoId;
    }

    @Override
    public int doStartTag() {
        JspWriter writer = pageContext.getOut();
        Integer visits = new AdminDAO(DBManager.getInstance()).getVisits(expoId);
        try {
            writer.println(visits);
        } catch (IOException e) {
            log.error(e);
        }
        return SKIP_BODY;
    }
}
