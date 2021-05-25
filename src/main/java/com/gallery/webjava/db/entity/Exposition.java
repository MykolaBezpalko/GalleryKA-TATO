package com.gallery.webjava.db.entity;

import java.util.Date;
import java.util.List;

public class Exposition extends Entity<Entity> {
    private String theme;
    private List<Hall> halls;
    private Date begin;
    private Date end;
    Integer price;
    private String description;
    private boolean isAvailable;
    private Language language;

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

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        return "Exposition{" +
                "theme='" + theme + '\'' +
                ", halls=" + halls +
                ", begin=" + begin +
                ", end=" + end +
                ", language=" + language.getLanguageName() +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
