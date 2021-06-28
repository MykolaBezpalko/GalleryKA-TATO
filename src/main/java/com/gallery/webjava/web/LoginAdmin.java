package com.gallery.webjava.web;

import com.gallery.webjava.db.AdminDAO;
import com.gallery.webjava.db.DBManager;
import com.gallery.webjava.db.entity.Administrator;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/admin")
public class LoginAdmin extends HttpServlet {
    RequestDispatcher dispatcher;
    HttpSession session;
    PrintWriter writer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        session = req.getSession();
        boolean ifAdmin = session.getAttribute("user") instanceof Administrator;
        writer = resp.getWriter();
        if(session.getAttribute("user") == null){
            writer.println("<html>" +
                    "<body style=\"text-align:center;\">" +
                    "<h1 style=\" text-align: center\" > You have no access to this page.</h1>" +
                    "<a href=\"http://localhost:8080/gallery/pages/admin-pages/login-as-admin.jsp?\">Please login as administrator</a>" +
                    "<br>" +
                    "<a href=\"http://localhost:8080/gallery\">Back to homepage </a>" +
                    "</body>" +
                    "</html>");
            return;
        }
        if (ifAdmin) {
            dispatcher = req.getRequestDispatcher("/check-data/admin/admin-cabinet");
            dispatcher.forward(req, resp);
//            resp.sendRedirect("../pages/admin-pages/admin-cabinet.jsp");
        } else {
            writer.println("<html>" +
                    "<body style=\"text-align:center;\">" +
                    "<h1 style=\" text-align: center\" > You are not Administrator</h1>" +
                    "<a href=\"http://localhost:8080/gallery\"> Back to homepage </a>" +
                    "</body>" +
                    "</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        writer = resp.getWriter();
        session = req.getSession();
        session.setAttribute("email", req.getParameter("email"));
        session.setAttribute("password", req.getParameter("password"));
        String email = session.getAttribute("email").toString();
        if (email == null || !checkAdmin(email)) {
            dispatcher = req.getRequestDispatcher("/pages/info-pages/not-admin.html");
            dispatcher.forward(req, resp);
        } else {
            String password = session.getAttribute("password").toString();
            Administrator admin = new AdminDAO(DBManager.getInstance()).getAdmin(email, password);
            session.setAttribute("user", admin);
            if (admin != null) {
                resp.sendRedirect("/gallery/admin/admin-cabinet");
            } else {
                dispatcher = req.getRequestDispatcher("/pages/info-pages/incorret-password.html");
                dispatcher.forward(req, resp);
                session.invalidate();
            }
        }
    }

    private boolean checkAdmin(String email) {
        Administrator u = new AdminDAO(DBManager.getInstance()).getAdminByEmail(email);
        if (u != null) {
            return true;
        }
        return false;
    }
}