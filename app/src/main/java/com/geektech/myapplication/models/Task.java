package com.geektech.myapplication.models;

import java.io.Serializable;

public class Task implements Serializable {
    public int id;
    public String title;
    public String image;
    public String description;

    public Task(int id, String title, String image, String description) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.description = description;
    }
}
