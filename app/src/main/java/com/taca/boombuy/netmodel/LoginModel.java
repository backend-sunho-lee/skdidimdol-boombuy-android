package com.taca.boombuy.netmodel;

/**
 * Created by jimin on 2017-02-13.
 */

//
public class LoginModel {
    String phone;
    String password;

    public LoginModel(){}

    @Override
    public String toString() {
        return "SignUpDTO{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
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

    public LoginModel(String phone, String password) {

        this.phone = phone;
        this.password = password;
    }
}
