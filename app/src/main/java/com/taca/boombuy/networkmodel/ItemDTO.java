package com.taca.boombuy.networkmodel;

/**
 * Created by jimin on 2017-02-24.
 */

public class ItemDTO {

    int id;
    int bid;
    String name;
    int price;
    String detail;
    String location;

    public ItemDTO() {
    }

    public ItemDTO(int id, int bid, String name, int price, String detail, String location) {
        this.id = id;
        this.bid = bid;
        this.name = name;
        this.price = price;
        this.detail = detail;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "id=" + id +
                ", bid=" + bid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", detail='" + detail + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
