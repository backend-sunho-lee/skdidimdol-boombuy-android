package com.taca.boombuy.networkmodel;

/**
 * Created by Tacademy on 2017-02-27.
 */

public class GiftSenderDTO {

    String phone;
    int cost;

    public GiftSenderDTO() {
    }

    public GiftSenderDTO(String phone, int cost) {
        this.phone = phone;
        this.cost = cost;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "GiftSenderDTO{" +
                "phone='" + phone + '\'' +
                ", cost=" + cost +
                '}';
    }
}
