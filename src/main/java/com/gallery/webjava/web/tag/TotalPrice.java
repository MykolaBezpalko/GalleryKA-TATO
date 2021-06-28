package com.gallery.webjava.web.tag;

import com.gallery.webjava.db.AdminDAO;
import com.gallery.webjava.db.DBManager;
import com.gallery.webjava.db.entity.Exposition;
import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class TotalPrice extends TagSupport {
    static final Logger log = Logger.getLogger(TotalPrice.class);
    Object chosenExpos;

    public void setChosenExpos(Object chosenExpos) {
        this.chosenExpos = chosenExpos;
    }

    @Override
    public int doStartTag() {
        Integer totalPrice = 0;
        for (String s : (List<String>) chosenExpos) {
            Exposition e = new AdminDAO(DBManager.getInstance()).getExpositionByName(s);
            totalPrice = totalPrice + e.getPrice();
        }
        JspWriter writer = pageContext.getOut();
        try {
            writer.println(totalPrice);
        } catch (IOException e) {
            log.error(e);
        }

        return SKIP_BODY;
    }
}
