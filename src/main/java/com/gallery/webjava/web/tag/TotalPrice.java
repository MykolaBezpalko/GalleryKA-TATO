package com.gallery.webjava.web.tag;

import com.gallery.webjava.db.AdminDAO;
import com.gallery.webjava.db.entity.Exposition;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

public class TotalPrice extends TagSupport {
    Object chosenExpos;


    public void setChosenExpos(Object chosenExpos) {
        this.chosenExpos = chosenExpos;
    }

    @Override
    public int doStartTag() throws JspException {
        Integer totalPrice = 0;
        for (String s : (List<String>) chosenExpos){
           Exposition e = new AdminDAO().getExpositionByName(s);
           totalPrice = totalPrice + e.getPrice();
        }
        JspWriter writer = pageContext.getOut();
        try {
            writer.println(totalPrice);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
