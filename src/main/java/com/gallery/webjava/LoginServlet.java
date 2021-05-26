package com.gallery.webjava;

import com.gallery.webjava.db.AdminDAO;
import com.gallery.webjava.db.Encoder;
import com.gallery.webjava.db.UserDAO;
import com.gallery.webjava.db.entity.Administrator;
import com.gallery.webjava.db.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

//check-data
public class LoginServlet extends HttpServlet {
    RequestDispatcher dispatcher;
    HttpSession session;
    PrintWriter writer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        writer = resp.getWriter();
        writer.println("Form already sent");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        writer = resp.getWriter();
        session = req.getSession();
        session.setAttribute("email", req.getParameter("email"));
        session.setAttribute("password", req.getParameter("password"));
        String email = session.getAttribute("email").toString();
        if(email == null || !checkUser(email)){
           dispatcher = req.getRequestDispatcher("./WEB-INF/info-pages/not-registered.html");
           dispatcher.forward(req, resp);
        }else{
            String password =  session.getAttribute("password").toString();
            Administrator adm = new AdminDAO().getAdmin(email,password);
            if(adm==null){
                User user = new UserDAO().getUserByEmail(email);
                writer.println(user);
            }else{
                writer.println(adm);
            }


//            writer.println(adm);
//            writer.println(Encoder.encode(password));
//            writer.println(password);
//            writer.println(Encoder.match(password,Encoder.encode(password)));
        }


    }

    public boolean checkUser(String email){
        Object u = new AdminDAO().getAdminByEmail(email);
        if(u != null){
            return true;
        }
        else{
            u = new UserDAO().getUserByEmail(email);
            if(u != null)
                return true;
        }
        return false;
    }
}
