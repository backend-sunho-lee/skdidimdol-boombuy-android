package com.taca.boombuy.modelRes;

import com.taca.boombuy.netmodel.ResHeader;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-17.
 */

public class ResBbSearchBrand {

    ResHeader header;

    ArrayList<ResBbSearchBrandBody> body;

    public ResHeader getHeader() {
        return header;
    }

    public void setHeader(ResHeader header) {
        this.header = header;
    }

    public ArrayList<ResBbSearchBrandBody> getBody() {
        return body;
    }

    public void setBody(ArrayList<ResBbSearchBrandBody> body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ResBbSearchBrand{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
