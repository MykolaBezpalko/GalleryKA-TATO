package com.gallery.webjava.web;

import com.gallery.webjava.db.AdminDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/admin-cabinet/all-expo/delete-expo")
public class DeleteExpo extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new AdminDAO().deleteExpo(getIdForDelete(req.getParameter("expo_id")));
        System.out.println("delete expo servlet works");

        resp.sendRedirect("http://localhost:8080/gallery/admin/admin-cabinet/all-expo");

    }

    public Integer getIdForDelete(String id){
        return Integer.valueOf(id);
    }
}
