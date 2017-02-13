package com.taca.boombuy.vo;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-09.
 */

public class VO_giftitem_group_info {


    ArrayList<VO_from_friends_info> sendPeople;
    String pay_state;

    String receivedPerson;
    String date;
    ArrayList<VO_giftitem_list> totalProductInfo;

    public String getPay_state() {
        return pay_state;
    }

    public void setPay_state(String pay_state) {
        this.pay_state = pay_state;
    }

    public ArrayList<VO_from_friends_info> getSendPeople() {
        return sendPeople;
    }

    public void setSendPeople(ArrayList<VO_from_friends_info> sendPeople) {
        this.sendPeople = sendPeople;
    }

    public String getReceivedPerson() {
        return receivedPerson;
    }

    public void setReceivedPerson(String receivedPerson) {
        this.receivedPerson = receivedPerson;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<VO_giftitem_list> getTotalProductInfo() {
        return totalProductInfo;
    }

    public void setTotalProductInfo(ArrayList<VO_giftitem_list> totalProductInfo) {
        this.totalProductInfo = totalProductInfo;
    }

    @Override
    public String toString() {
        return "VO_giftitem_group_info{" +
                "pay_state='" + pay_state + '\'' +
                ", sendPeople=" + sendPeople.toString() +
                ", receivedPerson='" + receivedPerson + '\'' +
                ", date='" + date + '\'' +
                ", totalProductInfo=" + totalProductInfo.toString() +
                '}';
    }
}
