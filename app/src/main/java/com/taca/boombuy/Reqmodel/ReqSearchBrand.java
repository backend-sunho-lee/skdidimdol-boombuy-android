package com.taca.boombuy.Reqmodel;

/**
 * Created by jimin on 2017-02-24.
 */

public class ReqSearchBrand {

    ReqHeader header;

    String bid;

    public ReqHeader getHeader() {
        return header;
    }

    public void setHeader(ReqHeader header) {
        this.header = header;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    @Override
    public String toString() {
        return "ReqSearchBrand{" +
                "header=" + header +
                ", bid='" + bid + '\'' +
                '}';
    }
}
