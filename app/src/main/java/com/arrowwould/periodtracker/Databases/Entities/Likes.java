package com.arrowwould.periodtracker.Databases.Entities;


public class Likes {
    String heading;
    int id;
    String title;

    public Likes() {
    }

    public Likes(int i, String str) {
        this.id = i;
        this.title = str;
    }

    public Likes(String str, int i) {
        this.id = i;
        this.heading = str;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getHeading() {
        return this.heading;
    }

    public void setHeading(String str) {
        this.heading = str;
    }
}