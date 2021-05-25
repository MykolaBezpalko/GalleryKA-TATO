package com.gallery.webjava.db.entity;

import java.io.Serializable;

public abstract class Entity<A extends Entity> implements Serializable {

    private static final long serialVersionUID = 8466257860808346236L;

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
