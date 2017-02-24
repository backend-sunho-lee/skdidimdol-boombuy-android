package com.taca.boombuy.networkmodel;

/**
 * Created by jimin on 2017-02-24.
 */

public class BrandDTO {

    int bid;
    String name;
    String location;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "BrandDTO{" +
                "bid=" + bid +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
