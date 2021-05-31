package com.gallery.webjava.db.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Exposition extends Entity<Entity> {
    private String theme;
    private List<Hall> halls = new ArrayList();
    private java.sql.Date begin;
    private java.sql.Date end;
    Integer price;
    private boolean isAvailable;

    public Exposition() {
        setTheme("No Theme");
        isAvailable = true;
    }

    public Exposition(String theme) {
        setTheme(theme);
    }

    public Date getBegin() {
        return begin;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setBegin(java.sql.Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(java.sql.Date end) {
        this.end = end;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public void addHall(Hall hall) {
        halls.add(hall);
    }

    @Override
    public String toString() {
        return "Exposition{" +
                " id= " + getId() +
                ", theme='" + theme + '\'' +
                ", halls=" + halls +
                ", begin=" + begin +
                ", end=" + end +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
