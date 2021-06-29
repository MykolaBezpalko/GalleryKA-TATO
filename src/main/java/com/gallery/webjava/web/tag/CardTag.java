package com.gallery.webjava.web.tag;


import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class CardTag extends TagSupport {
    static final Logger logger = Logger.getLogger(CardTag.class);
    String expoTheme;
    String hallName;
    String beginDate;
    String endDate;
    String price;

    public void setExpoTheme(String expoTheme) {
        this.expoTheme = expoTheme;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter writer = pageContext.getOut();
        try {
            writer.println("<div class=\"card\">\n" +
                    "<p class=\"hallName\">" + hallName + "</p>\n" +
                    "<div class=\"expo-photo\"></div>\n" +
                    "<p class=\"expoName\">" + expoTheme + "</p>" +
                    "<p class=\"period\">" + beginDate + " - " + endDate + " </p>\n" +
                    "<form action=\"user-cabinet/buy-ticket-pack\" method=\"GET\">\n" +
                    "    <input type=\"hidden\" value=\"" + expoTheme + "\" name=\"EXPO\">\n" +
                    "    <button type=\"buyBtn\"  class=\"price\">" + price + " UAH</button>\n" +
                    "</form>\n" +
                    "</div>");
        } catch (IOException e) {
            logger.error(e);
        }
        return SKIP_BODY;
    }
}
