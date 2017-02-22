package com.taca.boombuy.modelRes;

import com.taca.boombuy.netmodel.ResHeader;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-22.
 */

public class ResBbSearchItemCoupon{

    ResHeader header;

    ArrayList<ResBbSearchItemCouponBody> body;

    public ResHeader getHeader() {
        return header;
    }

    public void setHeader(ResHeader header) {
        this.header = header;
    }

    public ArrayList<ResBbSearchItemCouponBody> getBody() {
        return body;
    }

    public void setBody(ArrayList<ResBbSearchItemCouponBody> body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ResBbSearchItemCoupon{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
