package com.taca.boombuy.modelReq;

/**
 * Created by Tacademy on 2017-02-17.
 */

public class ReqBbSearchBrand {

    ReqHeader header;

    public ReqHeader getHeader() {
        return header;
    }

    public void setHeader(ReqHeader header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "ReqBbSearchBrand{" +
                "header=" + header +
                '}';
    }
}
