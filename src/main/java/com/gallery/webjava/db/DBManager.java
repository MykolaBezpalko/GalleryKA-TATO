package com.gallery.webjava.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
    private static DBManager dbManager;

    private DBManager() {
    }

    /**
     * Returns instance of singleton DBManager. Synchronized.
     *
     * @return DBManager instance
     */
    public static synchronized DBManager getInstance() {
        if (dbManager == null)
            dbManager = new DBManager();
        return dbManager;
    }

    /**
     * Returns a data base connection from the Connections Pool.
     *
     * @return A data base connection.
     */
    public Connection getConnection() {
        Context context;
        Connection connection = null;
        try {
            context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/gallery");
            connection = ds.getConnection();
            connection.setAutoCommit(false);
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void commitAndClose(Connection con) {
        try {
            if(con == null)
                 new NullPointerException("NULL connection");
            con.commit();
            con.close();
        } catch (SQLException  | NullPointerException e) {
            System.err.println("cant close connection");
            e.printStackTrace();
        }
    }

}
