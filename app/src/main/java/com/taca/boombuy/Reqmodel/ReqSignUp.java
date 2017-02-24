package com.taca.boombuy.Reqmodel;

import com.taca.boombuy.networkmodel.SignUpDTO;

/**
 * Created by jimin on 2017-02-13.
 */

public class ReqSignUp {
    ReqHeader header;
    SignUpDTO body;

    public ReqSignUp() {
    }

    public ReqSignUp(ReqHeader header, SignUpDTO body) {
        this.header = header;
        this.body = body;
    }

    public ReqHeader getHeader() {
        return header;
    }

    public void setHeader(ReqHeader header) {
        this.header = header;
    }

    public SignUpDTO getBody() {
        return body;
    }

    public void setBody(SignUpDTO body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ReqSignUp{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
