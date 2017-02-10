package com.taca.boombuy.netmodel;

/**
 * Created by jimin on 2017-02-10.
 */

public class ReqSendFcm {
    ReqHeader header;
    FCMModel body;

    public ReqHeader getHeader() {
        return header;
    }

    public void setHeader(ReqHeader header) {
        this.header = header;
    }

    public FCMModel getBody() {
        return body;
    }

    public void setBody(FCMModel body) {
        this.body = body;
    }
}
