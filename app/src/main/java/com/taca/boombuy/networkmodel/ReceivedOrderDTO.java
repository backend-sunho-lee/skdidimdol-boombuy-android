package com.taca.boombuy.networkmodel;

/**
 * Created by Tacademy on 2017-03-09.
 */

public class ReceivedOrderDTO {

    int oid;
    String time;
    String senders;
    String location;
    int iid;
    String item_name;
    String item_photo;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSenders() {
        return senders;
    }

    public void setSenders(String senders) {
        this.senders = senders;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_photo() {
        return item_photo;
    }

    public void setItem_photo(String item_photo) {
        this.item_photo = item_photo;
    }

    @Override
    public String toString() {
        return "ReceivedOrderDTO{" +
                "oid=" + oid +
                ", time='" + time + '\'' +
                ", senders='" + senders + '\'' +
                ", location='" + location + '\'' +
                ", iid=" + iid +
                ", item_name='" + item_name + '\'' +
                ", item_photo='" + item_photo + '\'' +
                '}';
    }
}
