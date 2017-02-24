package com.taca.boombuy.networkmodel;

/**
 * Created by jimin on 2017-02-24.
 */

public class LoginDTO {

    String phone;
    String password;

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


    public LoginDTO() {
    }

    public LoginDTO(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }
}
