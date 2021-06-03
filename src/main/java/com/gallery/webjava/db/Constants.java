package com.gallery.webjava.db;

/**
 * Represents list of all constants and queries for MySQL database 'gallery'
 */
public class Constants {
    public static final String PARSE_DATE = "yyyy-MM-dd";

    //fields
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String LANGUAGE_ID = "language_id";
    public static final String DESCRIPTION_TEXT = "description_text";
    public static final String EXPOSITION_ID = "exposition_id";
    public static final String HALL_ID = "hall_id";
    public static final String DESCRIPTION_ID = "description_id";
    public static final String START_DATE = "start_date";
    public static final String END_DATE = "end_date";
    public static final String PRICE = "price";
    public static final String USER_ID = "user_id";
    public static final String AVAILABLE = "available";

    //tables
    public static final String LANGUAGE = "language";
    public static final String ADMIN = "admin";
    public static final String USER = "user";
    public static final String DESCRIPTION = "description";
    public static final String EXPOSITION = "exposition";
    public static final String HALL = "hall";
    public static final String TICKET = "ticket";
    public static final String HALL_EXPOSITION = "hall_exposition";


    //QUERIES
    //hall
    protected static final String FIND_HALL_BY_NAME = "select * from " + HALL + " where name = ?";
    protected static final String FIND_HALL_BY_ID = "select * from " + HALL + " where id = ?";
    protected static final String FIND_ALL_HALLS = "select * from hall";
    protected static final String INSERT_HALL = "INSERT INTO " + HALL + '(' + NAME + ")value" + "(?)";
    protected static final String DELETE_HALL = "DELETE FROM " + HALL + " WHERE " + NAME + "= ?";

    //admin
    protected static final String GET_ALL_ADMINS = "select * from " + ADMIN;
    protected static final String CREATE_ADMINISTRATOR =
            "INSERT INTO " + ADMIN + '(' + NAME + ',' + EMAIL + ',' + PASSWORD + ')' + "values" + "(?,?,?)";
    protected static final String GET_ADMIN_BY_NAME = "SELECT * FROM " + ADMIN + " WHERE " + NAME + "=?";
    protected static final String GET_ADMIN_BY_EMAIL = "SELECT * FROM " + ADMIN + " WHERE " + EMAIL + "=?";
    protected static final String GET_ADMIN = "SELECT * FROM " + ADMIN + " WHERE " + EMAIL + "=? AND " + PASSWORD + "=?";
    protected static final String DELETE_ADMIN = "DELETE FROM " + ADMIN + " WHERE " + NAME + "= ?";

    //language
    protected static final String INSERT_LANG = "INSERT INTO " + LANGUAGE + '(' + NAME + ')' + "values" + "(?)";
    protected static final String GET_LANGUAGE_BY_NAME = "SELECT * FROM " + LANGUAGE + " WHERE " + NAME + "=?";
    protected static final String GET_LANGUAGE_BY_ID = "SELECT * FROM " + LANGUAGE + " WHERE " + ID + "=?";
    protected static final String GET_ALL_LANGUAGE = "SELECT * FROM " + LANGUAGE;
    protected static final String DELETE_LANGUAGE = "DELETE FROM " + LANGUAGE + " WHERE " + NAME + "= ?";

    //expo
    protected static final String GET_EXPO_BY_NAME = "SELECT * FROM " + EXPOSITION + " WHERE " + NAME + "=?";
    protected static final String GET_ALL_EXPO = "SELECT * FROM " + EXPOSITION;
    protected static final String INSERT_EXPO =
            "INSERT INTO " + EXPOSITION + '(' + NAME + ',' + START_DATE + ',' + END_DATE + ',' + PRICE + ')' + " VALUES (?,?,?,?)";
    protected static final String GET_EXPO_ID = "SELECT " + ID + " FROM " + EXPOSITION + " WHERE " + NAME + "=?";
    protected static final String INSERT_EXPO_ID =
            "INSERT INTO " + HALL_EXPOSITION + '(' + HALL_ID + ',' + EXPOSITION_ID + ')' + " value (?,?)";
    protected static final String INSERT_DESCRIPTION =
            "INSERT INTO " + DESCRIPTION + '(' + EXPOSITION_ID + ',' + DESCRIPTION_TEXT + ',' + LANGUAGE_ID + ')' + " VALUES (?,?,?)";
    protected static final String DELETE_EXPO = "DELETE FROM exposition WHERE id=?";
    protected static final String GET_EXPO_HALLS =
            "SELECT * FROM hall_exposition LEFT OUTER JOIN hall ON hall_exposition.hall_id = hall.id where exposition_id = ?";

    //user
    public static final String CREATE_USER =
            "INSERT INTO " + USER + '(' + NAME + ',' + PASSWORD + ',' + EMAIL + ')' + "values" + "(?,?,?)";
    protected static final String GET_USER_BY_EMAIL = "SELECT * FROM " + USER + " WHERE " + EMAIL + "=?";
    protected static final String GET_USER_ID = "SELECT " + ID + " FROM " + USER + " WHERE " + EMAIL + "=?";


}
