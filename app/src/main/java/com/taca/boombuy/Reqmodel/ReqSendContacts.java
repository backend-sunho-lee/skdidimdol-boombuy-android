package com.taca.boombuy.Reqmodel;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-24.
 */

public class ReqSendContacts {

    ArrayList<String> body;

    @Override
    public String toString() {
        return "ReqSendContacts{" +
                "body=" + body +
                '}';
    }

    public ArrayList<String> getBody() {
        return body;
    }

    public void setBody(ArrayList<String> body) {
        this.body = body;
    }

    public ReqSendContacts(ArrayList<String> body) {

        this.body = body;
    }

    public ReqSendContacts() {

    }
}
