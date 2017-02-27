package com.taca.boombuy.vo;

/**
 * Created by jimin on 2017-02-01.
 */

public class VO_from_friends_info {
    String name;
    String phone_num;
    String location;

    public VO_from_friends_info(){
    }


    @Override
    public String toString() {
        return "VO_from_friends_info{" +
                "name='" + name + '\'' +
                ", phone_num='" + phone_num + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    public VO_from_friends_info(String name, String phone_num, String location) {
        this.name = name;
        this.phone_num = phone_num;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPhone_num() {
        return phone_num;
    }
    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }
}