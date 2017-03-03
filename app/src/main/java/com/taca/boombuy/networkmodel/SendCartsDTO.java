package com.taca.boombuy.networkmodel;

/**
 * Created by Tacademy on 2017-03-03.
 */

public class SendCartsDTO {

    int oid;
    int iid;
    String name;
    int price;
    String location;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "SendCartsDTO{" +
                "oid=" + oid +
                ", iid=" + iid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", location='" + location + '\'' +
                '}';
    }
}
