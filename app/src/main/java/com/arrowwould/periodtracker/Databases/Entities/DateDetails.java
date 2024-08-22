package com.arrowwould.periodtracker.Databases.Entities;


public class DateDetails {
    String fertileDays;
    int id;
    String nextPeriod;
    String ovulationPeriod;
    String safeDays;

    public DateDetails(String str, String str2, String str3, String str4) {
        this.fertileDays = str;
        this.safeDays = str2;
        this.nextPeriod = str3;
        this.ovulationPeriod = str4;
    }

    public DateDetails() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getFertileDays() {
        return this.fertileDays;
    }

    public void setFertileDays(String str) {
        this.fertileDays = str;
    }

    public String getSafeDays() {
        return this.safeDays;
    }

    public void setSafeDays(String str) {
        this.safeDays = str;
    }

    public String getNextPeriod() {
        return this.nextPeriod;
    }

    public void setNextPeriod(String str) {
        this.nextPeriod = str;
    }

    public String getOvulationPeriod() {
        return this.ovulationPeriod;
    }

    public void setOvulationPeriod(String str) {
        this.ovulationPeriod = str;
    }
}
