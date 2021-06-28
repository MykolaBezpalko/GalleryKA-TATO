package com.gallery.webjava.db;

import com.gallery.webjava.web.Mapper;
import com.gallery.webjava.db.entity.*;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static com.gallery.webjava.db.Constants.*;

/**
 * Data access object for Administrator entity. Most of them used with administrator access servlets
 * all methods insert and remove table`s rows through transaction
 */
public class AdminDAO {

    private static final Logger log = Logger.getLogger(AdminDAO.class);
    private Manager dbManager;
    public AdminDAO(Manager dbManager){
        this.dbManager = dbManager;
    }

    /**
     * Insert new Administrator into data base 'gallery', table :'admin'
     *
     * @param admin Administrator object with name, e-mail and password fields
     */
    public void createAdmin(Administrator admin) {
        log.info("Start creation Administrator");
        Connection connection = null;
        PreparedStatement ps;
        String encodedPassword = Encoder.encode(admin.getPassword());
        try {
            connection = dbManager.getConnection();
            ps = connection.prepareStatement(CREATE_ADMINISTRATOR);
            ps.setString(1, admin.getName());
            ps.setString(2, admin.getEmail());
            ps.setString(3, encodedPassword);
            ps.executeUpdate();
            log.info("Finish creation Administrator");
        } catch (SQLException e) {
            log.error("Can`t create Administrator. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
    }

    /**
     * Remove administrator from database
     *
     * @param name Administrator Name in admin.name column
     */
    public void deleteAdmin(String name) {
        log.info("Start deleting Administrator");
        Connection connection = null;
        PreparedStatement ps;
        try {
            connection = dbManager.getConnection();
            ps = connection.prepareStatement(DELETE_ADMIN);
            ps.setString(1, name);
            ps.executeUpdate();
            log.info("Finish deleting Administrator");
        } catch (SQLException e) {
            log.error("Cant delete Administrator. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
    }

    /**
     * Looking for administrator in database its by name
     *
     * @param name Administrator Name in admin.name column
     * @return Administrator object mapped from database
     */
    public Administrator getAdminByName(String name) {
        log.info("Start search Administrator in database");
        Administrator admin = new Administrator("");
        Connection connection = null;
        PreparedStatement ps;
        ResultSet rs;
        AdminMapper mapper = new AdminMapper();
        try {
            connection = dbManager.getConnection();
            ps = connection.prepareStatement(GET_ADMIN_BY_NAME);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                admin = mapper.mapRow(rs);
            }
            log.info("Got Administrator from database");
        } catch (SQLException e) {
            log.error("Cant get Administrator from database." + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return admin;
    }

    /**
     * Looking for administrator in database its by name
     *
     * @param email Administrator Email in admin.email column
     * @return Administrator object mapped from database
     */
    public Administrator getAdminByEmail(String email) {
        log.info("Start looking for admin by its email");
        AdminMapper mapper = new AdminMapper();
        Administrator admin = null;
        Connection connection = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            connection = dbManager.getConnection();
            ps = connection.prepareStatement(GET_ADMIN_BY_EMAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                admin = mapper.mapRow(rs);
            }
            log.info("Got admin by email");
        } catch (SQLException e) {
            log.error("Cant get Administrator by EMAIL. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return admin;
    }

    /**
     * Looking for Administrator entity in database
     * NOTE: first should valid fields
     *
     * @param email    Administrator e-mail
     * @param password Administrator password
     * @return Administrator object
     */
    public Administrator getAdmin(String email, String password) {
        log.info("Start looking for Admin by email and password");
        AdminMapper mapper = new AdminMapper();
        Administrator admin = null;
        Connection connection = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            connection = dbManager.getConnection();
            ps = connection.prepareStatement(GET_ADMIN);
            ps.setString(1, email);
            ps.setString(2, Encoder.encode(password));
            rs = ps.executeQuery();
            while (rs.next()) {
                admin = mapper.mapRow(rs);
            }
            log.info("Finish looking for Admin by email and password");
        } catch (SQLException e) {
            log.error("Cant get Administrator by EMAIL and PASSWORD. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return admin;
    }

    /**
     * Looking for all administrator entities and return them in list
     *
     * @return List of all administrators from database
     */
    public List<Administrator> getAllAdmins() {
        log.info("Start looking for all administrators");
        List<Administrator> admins = new ArrayList<>();
        Connection connection = null;
        Statement st;
        ResultSet rs;
        try {
            connection = dbManager.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(GET_ALL_ADMINS);
            AdminMapper mapper = new AdminMapper();
            while (rs.next()) {
                admins.add(mapper.mapRow(rs));
            }
            log.info("Finish looking for all administrators");

        } catch (SQLException e) {
            log.error("cant get all admins list. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return admins;
    }

    /**
     * @param hallName String represents name of hall in hall.name
     * @return Hall object from database
     */
    public Hall getHallByName(String hallName) {
        log.info("Start looking for hall by its name.");
        Hall hall = new Hall();
        Connection connection = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            connection = dbManager.getConnection();
            ps = connection.prepareStatement(FIND_HALL_BY_NAME);
            ps.setString(1, hallName);
            rs = ps.executeQuery();
            HallMapper mapper = new HallMapper();
            while (rs.next()) {
                hall = mapper.mapRow(rs);
            }
            connection.commit();
            log.info("Finish looking for hall by its name.");
        } catch (SQLException e) {
            log.error("cant get hall." + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return hall;
    }

    /**
     * @param id Hall id should be equals hall.id from database
     * @return Hall object from database
     */
    public Hall getHallById(Integer id) {
        log.info("Start looking for hall by its id");
        Hall hall = new Hall();
        Connection connection = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            connection = dbManager.getConnection();
            ps = connection.prepareStatement(FIND_HALL_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            HallMapper mapper = new HallMapper();
            while (rs.next()) {
                hall = mapper.mapRow(rs);
            }
            connection.commit();
            log.info("Finish looking for hall by its id");
        } catch (SQLException e) {
            log.error("cant get hall by id. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return hall;
    }

    /**
     * Looking for all halls
     *
     * @return List<Hall> all halls from database with id or empty list if it`s no any
     */
    public List<Hall> getAllHalls() {
        log.info("Begin looking for all halls");
        List<Hall> allHalls = new ArrayList<>();
        Connection connection = null;
        Statement st;
        ResultSet rs;
        try {
            connection = dbManager.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(FIND_ALL_HALLS);
            HallMapper mapper = new HallMapper();
            while (rs.next()) {
                allHalls.add(mapper.mapRow(rs));
            }
            log.info("Finish looking for all halls");
        } catch (SQLException e) {
            log.error("cant get all Halls list. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return allHalls;
    }

    /**
     * Insert new hall into database.
     * NOTE: use it just for Administrator
     *
     * @param hallName name for a new Hall object
     */
    public void createHall(String hallName) {
        log.info("Creating new hall");
        Connection connection = null;
        PreparedStatement ps;
        try {
            connection = dbManager.getConnection();
            ps = connection.prepareStatement(INSERT_HALL);
            ps.setString(1, hallName);
            ps.executeUpdate();
            log.info("Finish creating new hall");
        } catch (SQLException e) {
            log.error("Can`t create hall. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
    }

    /**
     * Delete hall from database.
     * NOTE: use it just for Administrator
     *
     * @param hallName name for hall witch should be deleted
     */
    public void deleteHall(String hallName) {
        log.info("Deleting hall.");
        Connection connection = null;
        PreparedStatement ps;
        try {
            connection = dbManager.getConnection();
            ps = connection.prepareStatement(DELETE_HALL);
            ps.setString(1, hallName);
            ps.executeUpdate();
            log.info("Finish deleting hall.");

        } catch (SQLException e) {
            log.error("cant delete hall. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
    }

    /**
     * Insert new language into database
     *
     * @param lang new Language object
     */
    public void createLanguage(Language lang) {
        log.info("Begin insert language.");
        Connection connection = null;
        PreparedStatement ps;
        try {
            connection = dbManager.getConnection();
            ps = connection.prepareStatement(INSERT_LANG);
            ps.setString(1, lang.getLanguageName());
            ps.executeUpdate();
            log.info("Finish insert language.");
        } catch (SQLException e) {
            log.error("Can`t create new language. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
    }

    /**
     * Delete language from database.
     * NOTE: use it just for Administrator
     *
     * @param lang name for language witch should be deleted
     */
    public void deleteLanguage(String lang) {
        log.info("Begin deleting language.");
        Connection connection = null;
        PreparedStatement ps;
        try {
            connection = dbManager.getConnection();
            ps = connection.prepareStatement(DELETE_LANGUAGE);
            ps.setString(1, lang);
            ps.executeUpdate();
            log.info("Finish deleting language.");

        } catch (SQLException e) {
            log.error("cant delete language hall. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
    }

    /**
     * Find language in database by name
     *
     * @param lang name for compare
     * @return Language object
     */
    public Language getLanguageByName(String lang) {
        log.info("Begin looking for language.");
        Language language = new Language();
        LanguageMapper mapper = new LanguageMapper();
        Connection connection = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            connection = dbManager.getConnection();
            ps = connection.prepareStatement(GET_LANGUAGE_BY_NAME);
            ps.setString(1, lang);
            rs = ps.executeQuery();

            while (rs.next()) {
                language = mapper.mapRow(rs);
            }
            connection.commit();
            log.info("Finish looking for language.");
        } catch (SQLException e) {
            log.error("cant get language. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return language;
    }

    /**
     * Find language in database by its id
     *
     * @param id id for compare
     * @return Language object
     */
    public Language getLanguageById(Integer id) {
        log.info("Begin looking for language: " + id);
        Language language = new Language();
        Connection connection = null;
        LanguageMapper mapper = new LanguageMapper();
        PreparedStatement ps;
        ResultSet rs;
        try {
            connection = dbManager.getConnection();
            ps = connection.prepareStatement(GET_LANGUAGE_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                language = mapper.mapRow(rs);
            }
            connection.commit();
            log.info("Finish looking for language: " + id);
        } catch (SQLException e) {
            log.error("cant get language by ID:" + id + " .Cause: " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return language;
    }

    /**
     * Return list of Language objects from database
     *
     * @return List<Language> all language from database, list empty if not found any
     */
    public List<Language> getAllLanguages() {
        log.info("Begin looking for all language");
        List<Language> allLanguages = new ArrayList<>();
        Connection connection = null;
        LanguageMapper mapper = new LanguageMapper();
        Statement st;
        ResultSet rs;
        try {
            connection = dbManager.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(GET_ALL_LANGUAGE);
            while (rs.next()) {
                allLanguages.add(mapper.mapRow(rs));
            }
            log.info("Finish looking for all language");
        } catch (SQLException e) {
            log.error("cant get LANGUAGE list. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return allLanguages;
    }

    /**
     * Looking for exposition by its name
     *
     * @param theme name of exposition
     * @return Exposition object from database
     */
    public Exposition getExpositionByName(String theme) {
        log.info("Begin looking for exposition: " + theme);
        ExpositionMapper mapper = new ExpositionMapper(dbManager);
        Exposition expo = new Exposition();
        Connection connection = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            connection = dbManager.getConnection();
            ps = connection.prepareStatement(GET_EXPO_BY_NAME);
            ps.setString(1, theme);
            rs = ps.executeQuery();
            while (rs.next()) {
                expo = mapper.mapRow(rs);
            }
            connection.commit();
            log.info("Finish looking for exposition");
        } catch (SQLException e) {
            log.error("cant get Exposition by Name. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return expo;
    }

    /**
     * Looking for all Exposition in database
     *
     * @return List<Language> all expositions from database, list empty if not found any
     */
    public List<Exposition> getAllExpositions() {
        log.info("Begin looking for all expositions.");
        List<Exposition> allExpo = new ArrayList<>();
        Connection connection = null;
        ExpositionMapper mapper = new ExpositionMapper(dbManager);
        Statement st;
        ResultSet rs;
        try {
            connection = dbManager.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(GET_ALL_EXPO);
            while (rs.next()) {
                allExpo.add(mapper.mapRow(rs));
            }
            log.info("Begin looking for all expositions.");
        } catch (SQLException e) {
            log.error("cant get Exposition list. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return allExpo;
    }

    /**
     * Looking for all Exposition in database, sorting by begin_date desc.
     *
     * @return List<Language> all expositions from database, list empty if not found any
     */
    public List<Exposition> sortedExpoByTimeDesc() {
        log.info("Begin looking for sorted expositions");
        List<Exposition> allExpo = new ArrayList<>();
        ExpositionMapper mapper = new ExpositionMapper(dbManager);
        Connection connection = null;
        Statement st;
        ResultSet rs;
        try {
            connection = dbManager.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM exposition  where end_date >= now() order by start_date desc");

            while (rs.next()) {
                allExpo.add(mapper.mapRow(rs));
            }
            log.info("Finish looking for sorted expositions");
        } catch (SQLException e) {
            log.error("cant get sorted Exposition list. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return allExpo;
    }

    /**
     * Looking for all Exposition in database, sorting by begin_date asc.
     *
     * @return List<Language> all expositions from database, list empty if not found any
     */
    public List<Exposition> sortedExpoByTimeAsc() {
        log.info("Begin looking for sorted expositions");
        List<Exposition> allExpo = new ArrayList<>();
        ExpositionMapper mapper = new ExpositionMapper(dbManager);
        Connection connection = null;
        Statement st;
        ResultSet rs;
        try {
            connection = dbManager.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM exposition  where end_date >= now() order by start_date asc");

            while (rs.next()) {
                allExpo.add(mapper.mapRow(rs));
            }
            log.info("Finish looking for sorted expositions");
        } catch (SQLException e) {
            log.error("cant get sorted Exposition list. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return allExpo;
    }

    /**
     * Looking for all Exposition in database, sorting by PRICE asc.
     *
     * @return List<Language> all expositions from database, list empty if not found any
     */
    public List<Exposition> sortedExpoByPriceAsc() {
        log.info("Begin looking for price sorted expositions");
        List<Exposition> allExpo = new ArrayList<>();
        ExpositionMapper mapper = new ExpositionMapper(dbManager);
        Connection connection = null;
        Statement st;
        ResultSet rs;
        try {
            connection = dbManager.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM exposition  where end_date >= now() order by price asc");
            while (rs.next()) {
                allExpo.add(mapper.mapRow(rs));
            }
            log.info("Finish looking for price sorted expositions");
        } catch (SQLException e) {
            log.error("cant get price sorted Exposition list. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return allExpo;
    }

    /**
     * Looking for all Exposition in database, sorting by PRICE desc.
     *
     * @return List<Language> all expositions from database, list empty if not found any
     */
    public List<Exposition> sortedExpoByPriceDesc() {
        log.info("Begin looking for price sorted expositions");
        ExpositionMapper mapper = new ExpositionMapper(dbManager);
        List<Exposition> allExpo = new ArrayList<>();
        Connection connection = null;
        Statement st;
        ResultSet rs;
        try {
            connection = dbManager.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM exposition  where end_date >= now() order by price desc");
            while (rs.next()) {
                allExpo.add(mapper.mapRow(rs));
            }
            log.info("Finish looking for price sorted expositions");
        } catch (SQLException e) {
            log.error("cant get price sorted Exposition list. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return allExpo;
    }

    /**
     * Looking for all Exposition in database, sorting by NAME desc.
     *
     * @return List<Language> all expositions from database, list empty if not found any
     */
    public List<Exposition> sortedExpoByNameDesc() {
        log.info("Begin looking for name sorted expositions");
        List<Exposition> allExpo = new ArrayList<>();
        ExpositionMapper mapper = new ExpositionMapper(dbManager);
        Connection connection = null;
        Statement st;
        ResultSet rs;
        try {
            connection = dbManager.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM exposition  where end_date >= now() order by name desc");
            while (rs.next()) {
                allExpo.add(mapper.mapRow(rs));
            }
            log.info("Finish looking for name sorted expositions");

        } catch (SQLException e) {
            log.error("cant get name sorted Exposition list. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return allExpo;
    }

    /**
     * Looking for all Exposition in database, sorting by PRICE asc.
     *
     * @return List<Language> all expositions from database, list empty if not found any
     */
    public List<Exposition> sortedExpoByNameAsc() {
        log.info("Begin looking for name sorted expositions");
        List<Exposition> allExpo = new ArrayList<>();
        ExpositionMapper mapper = new ExpositionMapper(dbManager);
        Connection connection = null;
        Statement st;
        ResultSet rs;
        try {
            connection = dbManager.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery("SELECT * FROM exposition  where end_date >= now() order by name asc");
            while (rs.next()) {
                allExpo.add(mapper.mapRow(rs));
            }
            log.info("Finish looking for name sorted expositions");
        } catch (SQLException e) {
            log.error("cant get name sorted Exposition list. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return allExpo;
    }

    /**
     * Looking for all expo in database. Use it in user-cabinet
     *
     * @return list of all expositions witch finish AFTER today's date. List is empty if not find any
     */
    public List<Exposition> allExpositionsForUser() {
        log.info("Begin looking for expositions for user");
        List<Exposition> allExpo = new ArrayList<>();
        ExpositionMapper mapper = new ExpositionMapper(dbManager);
        Connection connection = null;
        Statement st;
        ResultSet rs;
        try {
            connection = dbManager.getConnection();
            st = connection.createStatement();
            rs = st.executeQuery(GET_ALL_EXPO_FROM_TODAY);
            while (rs.next()) {
                allExpo.add(mapper.mapRow(rs));
            }
            log.info("Finish looking for expositions for user");
        } catch (SQLException e) {
            log.error("cant get Exposition list for user. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return allExpo;
    }

    /**
     * Insert new line into exposition table, all fields getting from CreateExpo
     *
     * @param exposition object created by Administrator cabinet
     */
    public void createExposition(Exposition exposition) {
        log.info("Begin creation exposition");
        Connection connection = null;
        PreparedStatement ps;
        try {
            connection = dbManager.getConnection();
            ps = connection.prepareStatement(INSERT_EXPO);
            ps.setString(1, exposition.getTheme());
            ps.setDate(2, java.sql.Date.valueOf(exposition.getBegin().toString()));
            ps.setDate(3, java.sql.Date.valueOf(exposition.getEnd().toString()));
            ps.setInt(4, exposition.getPrice());
            ps.executeUpdate();
            log.info("Finish creation exposition");
        } catch (SQLException e) {
            log.error("can`t insert new expo in database. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
    }

    /**
     * @param name name of exposition for comparing
     * @return exposition id from database or NULL if not found any
     */
    public Integer getExpoId(String name) {
        log.info("Begin looking for exposition");
        Integer id = null;
        Connection connection = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            connection = dbManager.getConnection();
            ps = connection.prepareStatement(GET_EXPO_ID);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(ID);
            }
            if (id == null) {
                throw new IllegalStateException();
            }
            log.info("Begin looking for exposition");
        } catch (IllegalStateException | SQLException e) {
            log.error("Cant get Exposition ID. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }

        return id;
    }

    /**
     * Insert exposition and halls for it into database
     *
     * @param expo  Exposition object witch need to set halls
     * @param halls list of halls for Exposition object
     */
    public void settHallsForExpo(Exposition expo, List<Hall> halls) {
        log.info("Begin looking for Expo and Halls");
        Connection conn = null;
        PreparedStatement ps;
        try {
            conn = dbManager.getConnection();
            for (Hall hall : halls) {
                ps = conn.prepareStatement(INSERT_EXPO_ID);
                ps.setInt(1, hall.getId());
                ps.setInt(2, expo.getId());
                ps.executeUpdate();
            }
            log.info("Finish looking for Expo and Halls");
        } catch (SQLException e) {
            log.error("cant insert halls for exposition. " + e);
        } finally {
            dbManager.commitAndClose(conn);
        }
    }

    /**
     * find all halls for exposition and return list with Hall objects
     *
     * @param expo Exposition object
     * @return list of halls for this expo
     */
    public List<Hall> getHallsForExpo(Exposition expo) {
        log.info("Begin looking for halls.");
        List<Hall> halls = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            conn = dbManager.getConnection();
            ps = conn.prepareStatement(GET_EXPO_HALLS);
            ps.setInt(1, expo.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                Hall hall = new HallMapper().mapRow(rs);
                halls.add(hall);
            }
            log.info("Finish looking for halls.");
        } catch (SQLException e) {
            log.error("cant get halls for this expo " + expo.getTheme() + "\n cause: " + e);
        } finally {
            dbManager.commitAndClose(conn);
        }
        return halls;
    }

    /**
     * @param exposition  Exposition for witch set description
     * @param description Description for exposition
     * @param language    language id for description
     */
    public void setDescriptions(Exposition exposition, String description, int language) {
        log.info("Begin insert description");
        Connection conn = null;
        PreparedStatement ps;
        try {
            conn = dbManager.getConnection();
            ps = conn.prepareStatement(INSERT_DESCRIPTION);
            ps.setInt(1, exposition.getId());
            ps.setString(2, description);
            ps.setInt(3, language);
            ps.executeUpdate();
            log.info("Finish insert description");
        } catch (SQLException e) {
            log.error("Can`t insert description for expo: " + exposition.getTheme() +
                    "\n cause: " + e);
        } finally {
            dbManager.commitAndClose(conn);
        }
    }

    /**
     * Delete exposition from database by its ID
     *
     * @param id Exposition id
     */
    public void deleteExpo(Integer id) {
        log.info("Begin delete exception");
        Connection conn = null;
        PreparedStatement ps;
        try {
            conn = dbManager.getConnection();
            ps = conn.prepareStatement(DELETE_EXPO);
            ps.setInt(1, id);
            ps.executeUpdate();
            log.info("Finish delete exception");
        } catch (SQLException e) {
            log.error("cant delete expo. " + e);
        } finally {
            dbManager.commitAndClose(conn);
        }
    }

    /**
     * Count how many tickets was sold for this exposition
     *
     * @param id Exposition id
     * @return number of bought tickets
     */
    public Integer getVisits(Integer id) {
        log.info("Begin counting tickets for exception.");
        Integer visits = null;
        Connection connection = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            if (id == null) {
                throw new IllegalStateException();
            }
            connection = dbManager.getConnection();
            ps = connection.prepareStatement(GET_TICKETS_COUNT);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                visits = rs.getInt(1);
            }
            log.info("Finish counting tickets for exception.");
        } catch (IllegalStateException | SQLException e) {
            log.error("Cant get Ticket by Expo ID. " + e);
        } finally {
            dbManager.commitAndClose(connection);
        }
        return visits;
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
                log.error("cant map Admin. " + e);
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
                log.error("cant map Hall." + e);
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
                log.error("Can`t map Language. " + e);
            }
            return lang;
        }
    }

    /**
     * Extracts a Exposition object from the result set row.
     */
    private static class ExpositionMapper implements Mapper<Exposition> {
        private Manager dbManager;
        public ExpositionMapper(Manager dbManager){
            this.dbManager = dbManager;
        }
        @Override
        public Exposition mapRow(ResultSet resultSet) {
            Exposition exposition = new Exposition();
            try {
                exposition.setId(resultSet.getInt(ID));
                exposition.setTheme(resultSet.getString(NAME));
                exposition.setBegin(resultSet.getDate(START_DATE));
                exposition.setEnd(resultSet.getDate(END_DATE));
                exposition.setPrice(resultSet.getInt(PRICE));
                exposition.setHalls(new AdminDAO(dbManager).getHallsForExpo(exposition));
            } catch (SQLException e) {
                log.error("Can`t map exposition. " + e);
            }
            return exposition;
        }
    }

}
