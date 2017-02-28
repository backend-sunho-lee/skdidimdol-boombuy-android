package com.taca.boombuy.networkmodel;

/**
 * Created by Tacademy on 2017-02-27.
 */

public class ResMyProfileDTO {

    int uid;
    String phone;
    String name;
    String location;

    public ResMyProfileDTO() {
    }

    public ResMyProfileDTO(int uid, String phone, String name, String location) {
        this.uid = uid;
        this.phone = phone;
        this.name = name;
        this.location = location;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        return "ResMyProfileDTO{" +
                "uid=" + uid +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
