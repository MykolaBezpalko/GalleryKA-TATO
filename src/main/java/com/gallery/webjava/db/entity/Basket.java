package com.gallery.webjava.db.entity;

import java.util.*;

public class Basket extends Entity<Entity> {
    private User user;
    List<Ticket> boughtTickets = new ArrayList<>();

    public Basket(User user) {
        this.user = user;
    }

    public void setBoughtTickets(Ticket ticket) {
        boughtTickets.add(ticket);
    }

    public List<Ticket> getBoughtTickets() {
        return boughtTickets;
    }

    public void clearBasket() {
        boughtTickets.clear();
    }
}
