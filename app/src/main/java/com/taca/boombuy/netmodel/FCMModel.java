package com.taca.boombuy.netmodel;

/**
 * Created by jimin on 2017-02-10.
 */

public class FCMModel {
    String token;
    String content;

    public FCMModel(){
    }

    @Override
    public String toString() {
        return "FCMModel{" +
                "token='" + token + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public FCMModel(String token, String content) {
        this.token = token;
        this.content = content;
    }
}
