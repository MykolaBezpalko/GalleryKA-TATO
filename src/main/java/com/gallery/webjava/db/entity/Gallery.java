package com.gallery.webjava.db.entity;

import java.util.*;

public class Gallery extends Entity<Entity> {
    //singleton
    private static Gallery gallery;
    private static String GALLERY_NAME = "KA-TATO";
    private List<Exposition> expositions = new ArrayList<>();
    private List<Hall> halls = new ArrayList<>();
    private Integer visits;

    private Gallery() {
    }

    public static synchronized Gallery getGallery() {
        if (gallery == null)
            gallery = new Gallery();
        return gallery;
    }

    public static String getGalleryName() {
        return GALLERY_NAME;
    }

    public List<Exposition> getExpositions() {
        return expositions;
    }

    public void setExpositions(List<Exposition> expositions) {
        this.expositions = expositions;
    }

    public void setExpositions(Exposition exposition) {
        expositions.add(exposition);
    }

    public List<Hall> getHall() {
        return halls;
    }

    public void setHall(List<Hall> hall) {
        this.halls = hall;
    }

    public void setHall(Hall hall) {
        halls.add(hall);
    }

    public Integer getVisits() {
        return visits;
    }

    @Override
    public String toString() {
        return "Gallery{ name = '" + gallery.getGalleryName() + "' " +
                "expositions=" + expositions +
                ", halls=" + halls +
                '}';
    }
}