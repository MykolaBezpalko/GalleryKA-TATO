package com.gallery.webjava.db;

import com.gallery.webjava.web.Mapper;
import com.gallery.webjava.db.entity.*;

import static com.gallery.webjava.db.Constants.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data access object for Administrator entity.
 */
public class AdminDAO {
    /**
     * Insert Administrator object into database. Fields 'email' and 'password' are NULL
     *
     * @param admin Administrator object
     */
    public void createAdmin(Administrator admin) {
        Connection connection = null;
        PreparedStatement ps;
        String encodedPassword = Encoder.encode(admin.getPassword());
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(CREATE_ADMINISTRATOR);
            ps.setString(1, admin.getName());
            ps.setString(2, admin.getEmail());
            ps.setString(3, admin.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Can`t create Administrator");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    public void deleteAdmin(String name) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(DELETE_ADMIN);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("cant delete admin");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    public Administrator getAdminByName(String name) {
        Administrator admin = new Administrator("");
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        AdminMapper mapper = new AdminMapper();
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(GET_ADMIN_BY_NAME);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                admin = mapper.mapRow(rs);
            }
        } catch (SQLException e) {
            System.out.println("Cant get Administrator by NAME");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return admin;
    }

    public Administrator getAdminByEmail(String email) {
        Administrator admin = null;
        Connection connection = null;
        PreparedStatement ps;
        ResultSet rs;
        AdminMapper mapper = new AdminMapper();
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(GET_ADMIN_BY_EMAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                admin = mapper.mapRow(rs);
            }
        } catch (SQLException e) {
            System.out.println("Cant get Administrator by EMAIL");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return admin;
    }

    public Administrator getAdmin(String email, String password) {
        Administrator admin = null;
        Connection connection = null;
        PreparedStatement ps;
        ResultSet rs;
        AdminMapper mapper = new AdminMapper();
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(GET_ADMIN);
            ps.setString(1, email);
            ps.setString(2, Encoder.encode(password));
            rs = ps.executeQuery();
            while (rs.next()) {
                admin = mapper.mapRow(rs);
            }
        } catch (SQLException e) {
            System.out.println("Cant get Administrator by EMAIL and PASSWORD");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return admin;
    }

    public List<Administrator> getAllAdmins() {
        List<Administrator> admins = new ArrayList<>();
        Connection connection = null;
        Statement st = null;
        ResultSet rs;
        try {
            connection = DBManager.getInstance().getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(GET_ALL_ADMINS);
            AdminMapper mapper = new AdminMapper();
            while (rs.next()) {
                admins.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("cant get all admins list");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return admins;
    }

    public Hall getHallByName(String hallName) {
        Hall hall = new Hall();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(FIND_HALL_BY_NAME);
            ps.setString(1, hallName);
            rs = ps.executeQuery();
            HallMapper mapper = new HallMapper();
            while (rs.next()) {
                hall = mapper.mapRow(rs);
            }
            connection.commit();
        } catch (SQLException e) {
            System.err.println("cant get hall");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return hall;
    }

    public Hall getHallById(Integer id) {
        Hall hall = new Hall();
        Connection connection = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(FIND_HALL_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            HallMapper mapper = new HallMapper();
            while (rs.next()) {
                hall = mapper.mapRow(rs);
            }
            connection.commit();
        } catch (SQLException e) {
            System.err.println("cant get hall by id");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return hall;
    }

    /**
     * Return list of Hall objects from database
     *
     * @return List<Hall>
     */
    public List<Hall> getAllHalls() {
        List<Hall> allHalls = new ArrayList<>();
        Connection connection = null;
        Statement st = null;
        ResultSet rs;
        try {
            connection = DBManager.getInstance().getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(FIND_ALL_HALLS);
            HallMapper mapper = new HallMapper();
            while (rs.next()) {
                allHalls.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("cant get HALLS list");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return allHalls;
    }

    public void createHall(String hallName) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(INSERT_HALL);
            ps.setString(1, hallName);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Can`t create hall");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    public void deleteHall(String hallName) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(DELETE_HALL);
            ps.setString(1, hallName);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("cant find hall");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    /**
     * Insert new language into database
     */
    public void createLanguage(Language lang) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(INSERT_LANG);
            ps.setString(1, lang.getLanguageName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Can`t create new language");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    public void deleteLanguage(String lang) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(DELETE_LANGUAGE);
            ps.setString(1, lang);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("cant delete language hall");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    /**
     * Find language in database by name
     *
     * @param lang name for compare
     * @return Language object
     */
    public Language getLanguageByName(String lang) {
        Language language = new Language();
        Connection connection = null;
        PreparedStatement ps = null;
        LanguageMapper mapper = new LanguageMapper();
        ResultSet rs;
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(GET_LANGUAGE_BY_NAME);
            ps.setString(1, lang);
            rs = ps.executeQuery();

            while (rs.next()) {
                language = mapper.mapRow(rs);
            }
            connection.commit();
        } catch (SQLException e) {
            System.err.println("cant get language");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return language;
    }

    public Language getLanguageById(Integer id) {
        Language language = new Language();
        Connection connection = null;
        PreparedStatement ps = null;
        LanguageMapper mapper = new LanguageMapper();
        ResultSet rs;
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(GET_LANGUAGE_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                language = mapper.mapRow(rs);
            }
            connection.commit();
        } catch (SQLException e) {
            System.err.println("cant get language by ID");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return language;
    }

    /**
     * Return list of Language objects from database
     *
     * @return List<Language>
     */
    public List<Language> getAllLanguages() {
        List<Language> allLanguages = new ArrayList<>();
        Connection connection = null;
        LanguageMapper mapper = new LanguageMapper();
        Statement st = null;
        ResultSet rs;
        try {
            connection = DBManager.getInstance().getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(GET_ALL_LANGUAGE);

            while (rs.next()) {
                allLanguages.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("cant get LANGUAGE list");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return allLanguages;
    }

    public Exposition getExpositionByName(String theme) {
        Exposition expo = new Exposition();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs;
        ExpositionMapper mapper = new ExpositionMapper();
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(GET_EXPO_BY_NAME);
            ps.setString(1, theme);
            rs = ps.executeQuery();
            while (rs.next()) {
                expo = mapper.mapRow(rs);
            }
            connection.commit();
        } catch (SQLException e) {
            System.err.println("cant get Exposition by Name");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return expo;
    }

    public List<Exposition> getAllExpositions() {
        List<Exposition> allExpo = new ArrayList<>();
        Connection connection = null;
        ExpositionMapper mapper = new ExpositionMapper();
        Statement st = null;
        ResultSet rs;
        try {
            connection = DBManager.getInstance().getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(GET_ALL_EXPO);

            while (rs.next()) {
                allExpo.add(mapper.mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("cant get Exposition list");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
        return allExpo;
    }

    /**
     * Insert new line into exposition table, all fields getting from CreateExpo
     *
     * @param exposition object created by Administrator cabinet
     */
    public void createExposition(Exposition exposition) {
        Connection connection = null;
        PreparedStatement ps;
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(INSERT_EXPO);
            ps.setString(1, exposition.getTheme());
            //change
            ps.setDate(2, java.sql.Date.valueOf(exposition.getBegin().toString()));
            //change
            ps.setDate(3, java.sql.Date.valueOf(exposition.getEnd().toString()));

            ps.setInt(4, exposition.getPrice());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Can`t insert new expo in database");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }
    }

    public Integer getExpoId(String name) {
        Integer id = null;
        Connection connection = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            connection = DBManager.getInstance().getConnection();
            ps = connection.prepareStatement(GET_EXPO_ID);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(ID);
            }

            if (id == null) {
                throw new IllegalStateException();
            }
        } catch (IllegalStateException | SQLException e) {
            System.out.println("Cant get Exposition ID");
            e.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(connection);
        }

        return id;
    }

    public void settHallsForExpo(Exposition expo, List<Hall> halls) {
        Connection conn = null;
        PreparedStatement ps;
        try {
            conn = DBManager.getInstance().getConnection();
            for (Hall hall : halls) {
                ps = conn.prepareStatement(INSERT_EXPO_ID);
                ps.setInt(1, hall.getId());
                ps.setInt(2, expo.getId());
                ps.executeUpdate();
            }

        } catch (SQLException throwables) {
            System.out.println("cant insert halls for exposition");
            throwables.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(conn);
        }
    }

    public void setDescriptions(Exposition exposition, String description, int language) {
        Connection conn = null;
        PreparedStatement ps;
        try {
            conn = DBManager.getInstance().getConnection();
            ps = conn.prepareStatement(INSERT_DESCRIPTION);
            ps.setInt(1,exposition.getId());
            ps.setString(2,description);
            ps.setInt(3,language);
            System.out.println(description);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            DBManager.getInstance().commitAndClose(conn);
        }
    }

    /**
     * Extracts a Administrator object from the result set row.
     */
    private static class AdminMapper implements Mapper<Administrator> {

        @Override
        public Administrator mapRow(ResultSet resultSet) {
            try {
                Administrator admin = new Administrator("");
                admin.setId(resultSet.getInt(ID));
                admin.setPassword(resultSet.getString(PASSWORD));
                admin.setName(resultSet.getString(NAME));
                admin.setEmail(resultSet.getString(EMAIL));
                return admin;
            } catch (SQLException e) {
                System.err.println("cant get Admin");
                throw new IllegalStateException(e);
            }
        }
    }

    /**
     * Extracts a Hall object from the result set row.
     */
    private static class HallMapper implements Mapper<Hall> {
        @Override
        public Hall mapRow(ResultSet resultSet) {
            Hall hall = new Hall();
            try {
                hall.setId(resultSet.getInt(ID));
                hall.setHallName(resultSet.getString(NAME));
            } catch (SQLException e) {
                System.err.println("cant map Hall");
                e.printStackTrace();
            }

            return hall;
        }
    }

    /**
     * Extracts a Language object from the result set row.
     */
    private static class LanguageMapper implements Mapper<Language> {

        @Override
        public Language mapRow(ResultSet resultSet) {
            Language lang = new Language();
            try {
                lang.setId(resultSet.getInt(ID));
                lang.setLanguageName(resultSet.getString(NAME));
            } catch (SQLException e) {
                System.err.println("Can`t map Language");
                e.printStackTrace();
            }
            return lang;
        }
    }

    /**
     * Extracts a Exposition object from the result set row.
     */
    private static class ExpositionMapper implements Mapper<Exposition> {

        @Override
        public Exposition mapRow(ResultSet resultSet) {
            Exposition exposition = new Exposition();
            try {
                exposition.setId(resultSet.getInt(ID));
                exposition.setTheme(resultSet.getString(NAME));
                exposition.setBegin(resultSet.getDate(START_DATE));
                exposition.setEnd(resultSet.getDate(END_DATE));
                exposition.setPrice(resultSet.getInt(PRICE));
                exposition.setAvailable(resultSet.getBoolean(AVAILABLE));
            } catch (SQLException e) {
                System.err.println("Can`t map exposition");
                e.printStackTrace();
            }
            return exposition;
        }
    }

}
