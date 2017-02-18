package com.taca.boombuy.model;

/**
 * Created by Tacademy on 2017-02-17.
 */

public class ResBbSearchBrandBody {

    String name;
    String location;

    public ResBbSearchBrandBody() {
    }

    public ResBbSearchBrandBody(String name, String location) {
        this.name = name;
        this.location = location;
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
        return "ResBbSearchBrandBody{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
