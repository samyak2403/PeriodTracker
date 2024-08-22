package com.arrowwould.periodtracker.Databases.Entities;


public class Note {
    String date;
    int id;
    String note;

    public Note() {
    }

    public Note(String str, String str2) {
        this.date = str;
        this.note = str2;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String str) {
        this.note = str;
    }
}
