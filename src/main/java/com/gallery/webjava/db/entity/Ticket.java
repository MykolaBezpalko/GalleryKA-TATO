package com.gallery.webjava.db.entity;

public class Ticket extends Entity<Entity> {
    private int price;
    private Exposition exposition;

    public Ticket(int price, Exposition exposition) {
        setExposition(exposition);
        price = exposition.getPrice();
    }

    public int getPrice() {
        return price;
    }


    public Exposition getExposition() {
        return exposition;
    }

    public void setExposition(Exposition exposition) {
        this.exposition = exposition;
    }
}
