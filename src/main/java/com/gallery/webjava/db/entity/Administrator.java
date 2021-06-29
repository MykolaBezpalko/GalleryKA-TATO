package com.gallery.webjava.db.entity;

import java.util.ArrayList;
import java.util.List;

public class Administrator extends Entity<Entity> {
    private String email;
    private String password;
    private String name;
    static List<Administrator> allAdmins = new ArrayList<>();

    public Administrator(String name) {
        setName(name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static List<Administrator> getAllAdmins() {
        return allAdmins;
    }

    public static void setAllAdmins(List<Administrator> allAdmins) {
        Administrator.allAdmins = allAdmins;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
