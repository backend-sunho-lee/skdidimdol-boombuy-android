package com.taca.boombuy.networkmodel;

/**
 * Created by Tacademy on 2017-03-03.
 */

public class SendSettlementsDTO {

    int oid;
    String sender;
    String name;
    int cost;
    String state;
    String location;


    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "SendSettlementsDTO{" +
                "oid=" + oid +
                ", sender='" + sender + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", state='" + state + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
