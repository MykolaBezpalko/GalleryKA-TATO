package com.gallery.webjava.db.entity;

public class User extends Entity<Entity> {
    private Language language;
    private String userName;
    private String password;
    private String email;

    public User() { }

    public User(String email, String password) {
        setEmail(email);
        setEmail(password);
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{ Id= " + getId() +
//              ", language= " + language.getLanguageName() +
                ", userName= '" + userName + '\'' +
                ", email= '" + email + '\'' +
                '}';
    }
}
