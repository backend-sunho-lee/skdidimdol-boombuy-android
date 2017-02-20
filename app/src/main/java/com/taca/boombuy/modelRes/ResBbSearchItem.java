package com.taca.boombuy.modelRes;

import com.taca.boombuy.netmodel.ResHeader;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-17.
 */

public class ResBbSearchItem {

    ResHeader header;

    ArrayList<ResBbSearchItemBody> body;

    public ResBbSearchItem() {
    }

    public ResBbSearchItem(ResHeader header, ArrayList<ResBbSearchItemBody> body) {
        this.header = header;
        this.body = body;
    }

    public ResHeader getHeader() {
        return header;
    }

    public void setHeader(ResHeader header) {
        this.header = header;
    }

    public ArrayList<ResBbSearchItemBody> getBody() {
        return body;
    }

    public void setBody(ArrayList<ResBbSearchItemBody> body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ResBbSearchItem{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
