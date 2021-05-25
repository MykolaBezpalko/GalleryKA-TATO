package com.gallery.webjava.db;

import com.gallery.webjava.Mapper;
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

    public User getUserByEmail(String email){
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
