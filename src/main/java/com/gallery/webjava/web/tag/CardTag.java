package com.gallery.webjava.web.tag;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class CardTag extends TagSupport {
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
            writer.println("<div class=\"card\" style=\"width: 300px; height: 350px; background-color: aliceblue;\">\n" +
                    "<h3 class=\"hallName\">" + hallName + "</h3>\n" +
                    "<div class=\"expo-photo\" style=\"width: 100%; height: 200px;\"></div>\n" +
                    "<p class=\"expoName\">" + expoTheme + "</p>" +
                    "<p class=\"period\">" + beginDate + " - " + endDate + " </p>\n" +
                    "<form action=\"user-cabinet/buy-ticket-pack\" method=\"GET\">\n" +
                    "    <input type=\"hidden\" value=\"" + expoTheme + "\" name=\"EXPO\">\n" +
                    "    <button type=\"buyBtn\"  class=\"price\">" + price + "</button>\n" +
                    "</form>\n" +
                    "</div>");
        } catch (IOException e) {

            e.printStackTrace();
        }

        return SKIP_BODY;
    }
}
