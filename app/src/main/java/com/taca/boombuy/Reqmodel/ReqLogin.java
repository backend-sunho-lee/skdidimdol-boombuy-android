package com.taca.boombuy.Reqmodel;

import com.taca.boombuy.networkmodel.LoginDTO;

/**
 * Created by jimin on 2017-02-24.
 */

public class ReqLogin {

    ReqHeader header;
    LoginDTO body;

    public ReqLogin() {
    }

    public ReqLogin(ReqHeader header, LoginDTO body) {
        this.header = header;
        this.body = body;
    }

    public ReqHeader getHeader() {
        return header;
    }

    public void setHeader(ReqHeader header) {
        this.header = header;
    }

    public LoginDTO getBody() {
        return body;
    }

    public void setBody(LoginDTO body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ReqLogin{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
