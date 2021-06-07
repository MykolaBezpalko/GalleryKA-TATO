package com.gallery.webjava.web.tag;

import com.gallery.webjava.db.AdminDAO;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;



public class TicketsCountTag extends TagSupport {
    Integer expoId;

    public void setExpoId(Integer expoId) {
        this.expoId = expoId;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter writer = pageContext.getOut();
        Integer visits = new AdminDAO().getVisits(expoId);
        try {
            writer.println(visits);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
