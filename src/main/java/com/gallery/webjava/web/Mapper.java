package com.gallery.webjava.web;

import java.sql.ResultSet;

/**
 * Use it for mapping entity from data base result set.
 * Do not use method next() for moving cursor in implementation.
 * This is just for extracting data from table.
 */
public interface Mapper<T> {
    T mapRow(ResultSet resultSet);
}
