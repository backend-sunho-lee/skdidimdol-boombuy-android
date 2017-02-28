package com.taca.boombuy.Reqmodel;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-24.
 */

public class ReqSendContacts {

    ArrayList<String> phone;

    @Override
    public String toString() {
        return "ReqSendContacts{" +
                "phone=" + phone +
                '}';
    }

    public ArrayList<String> getPhone() {
        return phone;
    }

    public void setPhone(ArrayList<String> phone) {
        this.phone = phone;
    }

    public ReqSendContacts(ArrayList<String> phone) {

        this.phone = phone;
    }

    public ReqSendContacts() {

    }
}
