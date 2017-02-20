package com.taca.boombuy.modelRes;

import com.taca.boombuy.netmodel.ResHeader;

/**
 * Created by Tacademy on 2017-02-19.
 */
public class ResBbSearchItemId {

    ResHeader header;

    ResBbSearchItemBody body;

    public ResBbSearchItemId() {

    }

    public ResBbSearchItemId(ResHeader header, ResBbSearchItemBody body) {
        this.header = header;
        this.body = body;
    }

    public ResHeader getHeader() {
        return header;
    }

    public void setHeader(ResHeader header) {
        this.header = header;
    }

    public ResBbSearchItemBody getBody() {
        return body;
    }

    public void setBody(ResBbSearchItemBody body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ResBbSearchItemId{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
