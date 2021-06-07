package com.gallery.webjava.db;

import com.gallery.webjava.db.entity.Exposition;
import com.gallery.webjava.web.Mapper;
import com.gallery.webjava.db.entity.User;

import static com.gallery.webjava.db.Constants.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public void createUser(User user) {
        Connection connection = null;
        PreparedStatement ps;
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(CREATE_USER);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Can`t create User");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    public User getUserByEmail(String email) {
        User user = null;
        Connection connection = null;
        PreparedStatement ps;
        ResultSet rs;
        UserMapper mapper = new UserMapper();
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(GET_USER_BY_EMAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = mapper.mapRow(rs);
            }
        } catch (SQLException e) {
            System.out.println("Cant get USER by EMAIL");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return user;
    }

    public User getUser(String email, String password) {
        User user = null;
        Connection connection = null;
        PreparedStatement ps;
        ResultSet rs;
        UserMapper mapper = new UserMapper();
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(GET_USER);
            ps.setString(1, email);
            ps.setString(2, Encoder.encode(password));
            rs = ps.executeQuery();
            while (rs.next()) {
                user = mapper.mapRow(rs);
            }
        } catch (SQLException e) {
            System.err.println("Cant get USER by EMAIL and password");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return user;
    }

    public Integer getUserId(String email) {
        Integer id = null;
        Connection conn = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            conn = DBManager.getInstance().getConnection();
            ps = conn.prepareStatement(GET_USER_ID);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(ID);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            DBManager.getInstance().commitAndClose(conn);
        }
        return id;
    }



    public  void createTicket(User user, Exposition exposition){
        Connection conn = null;
        PreparedStatement ps;
        try{
            conn = DBManager.getInstance().getConnection();
            ps = conn.prepareStatement(CREATE_TICKET);
            ps.setInt(1,user.getId());
            ps.setInt(2,exposition.getId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            DBManager.getInstance().commitAndClose(conn);
        }
    }


    /**
     * Extracts a User object from the result set row.
     */
    private static class UserMapper implements Mapper<User> {

        @Override
        public User mapRow(ResultSet resultSet) {
            User user = new User();
            try {
                user.setId(resultSet.getInt(ID));
                user.setUserName(resultSet.getString(NAME));
                user.setPassword(resultSet.getString(PASSWORD));
                user.setEmail(resultSet.getString(EMAIL));
                user.setLanguage(new AdminDAO().getLanguageById(resultSet.getInt(LANGUAGE_ID)));
            } catch (SQLException e) {
                System.err.println("Can`t map exposition");
                e.printStackTrace();
            }
            return user;
        }
    }
}
