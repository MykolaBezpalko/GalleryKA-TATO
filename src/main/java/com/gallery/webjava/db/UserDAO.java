package com.gallery.webjava.db;

import com.gallery.webjava.db.entity.Exposition;
import com.gallery.webjava.web.Mapper;
import com.gallery.webjava.db.entity.User;
import org.apache.log4j.Logger;

import static com.gallery.webjava.db.Constants.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Represents methods for changing database or get data from it for user
 */
public class UserDAO {
    private static final Logger log = Logger.getLogger(UserDAO.class);
    private Manager dbManager;
    public UserDAO(Manager dbManager){
        this.dbManager = dbManager;
    }
    /**
     * Insert new User into database
     * @param user new User
     */
    public void createUser(User user) {
        log.info("Begin insert user into database");
        Connection connection = null;
        PreparedStatement ps;
        try {
            connection = dbManager.getConnection();
            ps = connection.prepareStatement(CREATE_USER);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
            log.info("Finish insert user into database");
        } catch (SQLException e) {
            log.error("Can`t create User. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
    }

    /**
     * Get User entity from database by its email
     * @param email user`s email
     * @return null if not find it or User object if it`s in database
     */
    public User getUserByEmail(String email) {
        log.info("Begin looking for user into database");
        UserMapper mapper = new UserMapper();
        User user = null;
        Connection connection = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            connection = dbManager.getConnection();
            ps = connection.prepareStatement(GET_USER_BY_EMAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                user = mapper.mapRow(rs);
            }
            log.info("Finish looking for user into database");
        } catch (SQLException e) {
            log.error("Cant get USER by EMAIL. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return user;
    }

    /**
     * Looking and maping User object from database
     * @param email User email
     * @param password user password
     * @return User object from database
     */
    public User getUser(String email, String password) {
        log.info("Begin looking for user by email and password");

        User user = null;
        Connection connection = null;
        PreparedStatement ps;
        ResultSet rs;
        UserMapper mapper = new UserMapper();
        try {
            connection = dbManager.getConnection();
            ps = connection.prepareStatement(GET_USER);
            ps.setString(1, email);
            ps.setString(2, Encoder.encode(password));
            rs = ps.executeQuery();
            while (rs.next()) {
                user = mapper.mapRow(rs);
            }
            log.info("Finish looking for user by email and password");
        } catch (SQLException e) {
            log.error("Cant get USER by EMAIL and password. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return user;
    }

    /**
     * Looking for user in database by its email
     * @param email user`s email
     * @return user id from database
     */
    public Integer getUserId(String email) {
        log.info("Begin looking for user`s id by email");
        Integer id = null;
        Connection conn = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            conn = dbManager.getConnection();
            ps = conn.prepareStatement(GET_USER_ID);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(ID);
            }
            log.info("Finish looking for user`s id by email");
        } catch (SQLException e) {
            log.error("Cant find user id with email: " + email + "\ncause: " + e );
        }finally{
            dbManager.commitAndClose(conn);
        }
        return id;
    }

    /**
     * Create ticket for user and insert it into database
     * @param user user for this ticket
     * @param exposition exposition for this ticket
     */
    public  void createTicket(User user, Exposition exposition){
        log.info("Begin insert ticket into database");
        Connection conn = null;
        PreparedStatement ps;
        try{
            conn = dbManager.getConnection();
            ps = conn.prepareStatement(CREATE_TICKET);
            ps.setInt(1,user.getId());
            ps.setInt(2,exposition.getId());
            ps.executeUpdate();
            log.info("Finish insert ticket into database");
        } catch (SQLException e) {
            log.error("Cant insert ticket for user. " + e);
        }finally{
            dbManager.commitAndClose(conn);
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
                user.setLanguage(new AdminDAO(DBManager.getInstance()).getLanguageById(resultSet.getInt(LANGUAGE_ID)));
            } catch (SQLException e) {
                log.error("Can`t map exposition. " + e);
            }
            return user;
        }
    }
}
