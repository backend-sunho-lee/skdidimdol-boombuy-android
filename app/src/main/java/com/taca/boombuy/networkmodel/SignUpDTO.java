package com.taca.boombuy.networkmodel;

/**
 * Created by jimin on 2017-02-24.
 */

public class SignUpDTO {
    String phone;
    String password;
    String name;
    String token;

    @Override
    public String toString() {
        return "SignUpDTO{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
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

    public SignUpDTO(String phone, String password, String name, String token) {

        this.phone = phone;
        this.password = password;
        this.name = name;
        this.token = token;
    }

    public SignUpDTO() {

    }
}
