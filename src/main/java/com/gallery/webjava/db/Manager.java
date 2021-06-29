package com.gallery.webjava.db;

import java.sql.Connection;

public interface Manager {


    static Manager getInstance() {
        return null;
    }

    Connection getConnection();

    void commitAndClose(Connection connection);
}
