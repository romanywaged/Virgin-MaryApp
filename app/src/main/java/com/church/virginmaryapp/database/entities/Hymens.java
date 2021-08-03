package com.church.virginmaryapp.database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Hymens {

    @PrimaryKey(autoGenerate = true)
    public int id;

    String name;

    String words;


    public Hymens() {
    }

    public Hymens(String name, String words) {
        this.name = name;
        this.words = words;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }
}
