package com.taca.boombuy.modelReq;

/**
 * Created by Tacademy on 2017-02-19.
 */

public class ReqBbSearchItemId {

    ReqHeader header;

    int body;

    public ReqBbSearchItemId() {
    }

    public ReqBbSearchItemId(ReqHeader header, int body) {
        this.header = header;
        this.body = body;
    }

    public ReqHeader getHeader() {
        return header;
    }

    public void setHeader(ReqHeader header) {
        this.header = header;
    }

    public int getBody() {
        return body;
    }

    public void setBody(int body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ReqBbSearchItemId{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
