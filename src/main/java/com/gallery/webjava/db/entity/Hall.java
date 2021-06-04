package com.gallery.webjava.db.entity;

public class Hall extends Entity<Entity> {
    private String hallName;
    public Hall() {
    }

    public Hall(String hallName) {
        setHallName(hallName);
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "name= '" + hallName + '\'' +
                ", id= " + getId() +
                '}';
    }
}
