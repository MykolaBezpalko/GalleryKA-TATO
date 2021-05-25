package com.gallery.webjava.db.entity;

import java.util.*;

public class Language extends Entity {
    private static List<Language> languages = new ArrayList<>();
    private String language;

    public Language() {
    }

    public Language(String lang) {
        setLanguageName(lang);
    }

    public List<Language> getLanguagesList() {
        return languages;
    }

    public String getLanguageName() {
        return language;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public void setLanguageName(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Language{" +
                "language= '" + language + '\'' +
                "id= '" + getId() + '\'' +
                '}';
    }
}