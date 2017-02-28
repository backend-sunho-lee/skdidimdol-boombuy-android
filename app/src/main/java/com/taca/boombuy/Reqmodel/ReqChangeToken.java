package com.taca.boombuy.Reqmodel;

/**
 * Created by jimin on 2017-02-28.
 */

public class ReqChangeToken {
    String token;

    @Override
    public String toString() {
        return "ReqChangeToken{" +
                "token='" + token + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ReqChangeToken(String token) {

        this.token = token;
    }

    public ReqChangeToken() {

    }
}
