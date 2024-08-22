package com.arrowwould.periodtracker.Model;


public class OvulationData {
    private final String articlesId;
    private final int bgColor;
    private final int color;
    private final String date;
    private final String daysLeft;
    private final String heading;
    private final int icon;

    public OvulationData(int i, String str, String str2, String str3, String str4, int i2, int i3) {
        this.icon = i;
        this.heading = str;
        this.date = str2;
        this.daysLeft = str3;
        this.articlesId = str4;
        this.bgColor = i2;
        this.color = i3;
    }

    public int getIcon() {
        return this.icon;
    }

    public String getHeading() {
        return this.heading;
    }

    public String getDate() {
        return this.date;
    }

    public String getDaysLeft() {
        return this.daysLeft;
    }

    public String getArticlesId() {
        return this.articlesId;
    }

    public int getBgColor() {
        return this.bgColor;
    }

    public int getColor() {
        return this.color;
    }
}
