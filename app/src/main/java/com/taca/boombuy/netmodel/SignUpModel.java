package com.taca.boombuy.netmodel;

/**
 * Created by jimin on 2017-02-13.
 */

public class SignUpModel {
    String phone;
    String password;
    String name;
    String token;
    String profile;

    public SignUpModel(){}

    @Override
    public String toString() {
        return "SignUpModel{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", profile='" + profile + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public SignUpModel(String phone, String password, String name, String token, String profile) {

        this.phone = phone;
        this.password = password;
        this.name = name;
        this.token = token;
        this.profile = profile;
    }
}
