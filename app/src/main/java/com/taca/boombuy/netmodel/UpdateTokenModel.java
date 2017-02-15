package com.taca.boombuy.netmodel;

/**
 * Created by jimin on 2017-02-13.
 */

public class UpdateTokenModel {
    String phone;
    String token;

    public UpdateTokenModel(){}

    @Override
    public String toString() {
        return "SignUpModel{" +
                "phone='" + phone + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UpdateTokenModel(String phone, String token) {

        this.phone = phone;
        this.token = token;
    }
}
