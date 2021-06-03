package com.gallery.webjava.web.locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@WebServlet("/lang-ua")
public class LocalLanguage extends HttpServlet {
    Locale locale;
    HttpSession session;
    RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();
        locale = new Locale("uk", "UA");
        System.out.println("lang ua work");
        session.setAttribute("locale", locale);
        resp.setLocale(locale);
        resp.sendRedirect("/gallery");



    }
}
