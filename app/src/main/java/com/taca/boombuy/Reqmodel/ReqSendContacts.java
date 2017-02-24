package com.taca.boombuy.Reqmodel;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-24.
 */

public class ReqSendContacts {

    ReqHeader header;
    ArrayList<String> body;

    public ReqHeader getHeader() {
        return header;
    }

    public void setHeader(ReqHeader header) {
        this.header = header;
    }

    public ArrayList<String> getBody() {
        return body;
    }

    public void setBody(ArrayList<String> body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ReqSendContacts{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
