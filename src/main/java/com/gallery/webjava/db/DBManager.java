package com.gallery.webjava.db;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
    private static DBManager dbManager;
    private static final Logger log = Logger.getLogger(DBManager.class);
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
            log.error("Database Connection fail" + e);
        }
        return connection;
    }

    /**
     * Close connection and commit changes
     * @param con connection to database
     */
    public void commitAndClose(Connection con) {
        try {
            if(con == null){
                throw new NullPointerException("connection is NULL");
            }
            con.commit();
            con.close();
        } catch (SQLException  | NullPointerException e) {
            log.error("cant close connection." + e);
        }
    }

}
