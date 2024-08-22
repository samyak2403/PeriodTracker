package com.arrowwould.periodtracker.Model;


public class Blog {
    String body;
    String color;
    String heading;
    String imgPath;
    boolean isDark;

    public Blog(String str, String str2, String String, String str3, boolean z) {
        this.heading = str;
        this.body = str2;
        this.imgPath = String;
        this.color = str3;
        this.isDark = z;
    }

    public Blog(String str, String i) {
        this.heading = str;
        this.imgPath = i;
    }

    public Blog() {
    }

    public String getColor() {
        return this.color;
    }

    public boolean isDark() {
        return this.isDark;
    }

    public void setDark(boolean z) {
        this.isDark = z;
    }

    public void setColor(String str) {
        this.color = str;
    }

    public String getHeading() {
        return this.heading;
    }

    public void setHeading(String str) {
        this.heading = str;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String str) {
        this.body = str;
    }

    public String getImgPath() {
        return this.imgPath;
    }

    public void setImgPath(String i) {
        this.imgPath = i;
    }
}
