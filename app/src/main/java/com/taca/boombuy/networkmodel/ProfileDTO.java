package com.taca.boombuy.networkmodel;

/**
 * Created by jimin on 2017-02-24.
 */

public class ProfileDTO {

    String phone;
    String name;
    String token;
    String profile_url;


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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    @Override
    public String toString() {
        return "ProfileDTO{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", token='" + token + '\'' +
                ", profile_url='" + profile_url + '\'' +
                '}';
    }
}
