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

    public Exposition() {
        setTheme("No Theme");
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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public void setHalls(List<Hall> halls){
        this.halls = halls;
    }

    public void addHall(Hall hall) {
        halls.add(hall);
    }

    public String getHallsString(){
        StringBuilder sb = new StringBuilder();
        for(Hall h : halls){
            sb.append(h.getHallName()).append(',');
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Exposition{" +
                " id= " + getId() +
                ", theme='" + theme + '\'' +
                ", halls=" + halls +
                ", begin=" + begin +
                ", end=" + end +
                '}';
    }
}
