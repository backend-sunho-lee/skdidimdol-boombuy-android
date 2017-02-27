package com.taca.boombuy.networkmodel;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-27.
 */

public class GiftDTO {

    ArrayList<Integer> carts;

    String receiver;

    ArrayList<GiftSenderDTO> senders;

    public GiftDTO() {
    }

    public GiftDTO(ArrayList<Integer> carts, String receiver, ArrayList<GiftSenderDTO> senders) {
        this.carts = carts;
        this.receiver = receiver;
        this.senders = senders;
    }

    public ArrayList<Integer> getCarts() {
        return carts;
    }

    public void setCarts(ArrayList<Integer> carts) {
        this.carts = carts;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public ArrayList<GiftSenderDTO> getSenders() {
        return senders;
    }

    public void setSenders(ArrayList<GiftSenderDTO> senders) {
        this.senders = senders;
    }

    @Override
    public String toString() {
        return "GiftDTO{" +
                "carts=" + carts +
                ", receiver='" + receiver + '\'' +
                ", senders=" + senders +
                '}';
    }
}
