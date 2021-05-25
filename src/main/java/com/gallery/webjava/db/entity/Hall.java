package com.gallery.webjava.db.entity;

public class Hall extends Entity<Entity> {
    private String hallName;
    private Exposition exposition;

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

    public Exposition getExposition() {
        return exposition;
    }

    public void setExposition(Exposition exposition) {
        this.exposition = exposition;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "name= '" + hallName + '\'' +
                ", exposition= " + exposition +
                ", id= " + getId() +
                '}';
    }
}
