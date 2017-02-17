package com.taca.boombuy.vo;

/**
 * Created by jimin on 2017-02-01.
 */

public class VO_from_friends_info {
    String name;
    String phone_num;

    public VO_from_friends_info(){
    }

    @Override
    public String toString() {
        return name + " ";
    }

    public VO_from_friends_info(String name, String phone_num) {
        this.name = name;
        this.phone_num = phone_num;
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