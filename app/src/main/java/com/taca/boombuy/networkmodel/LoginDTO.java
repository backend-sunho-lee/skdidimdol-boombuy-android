package com.taca.boombuy.networkmodel;

/**
 * Created by jimin on 2017-02-24.
 */

public class LoginDTO {

    String phone;
    String password;
    String token;

    @Override
    public String toString() {
        return "LoginDTO{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginDTO(String phone, String password, String token) {

        this.phone = phone;
        this.password = password;
        this.token = token;
    }

    public LoginDTO() {

    }
}
