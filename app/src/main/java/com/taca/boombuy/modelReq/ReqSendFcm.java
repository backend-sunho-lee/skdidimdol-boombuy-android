package com.taca.boombuy.modelReq;

import com.taca.boombuy.Reqmodel.ReqHeader;
import com.taca.boombuy.netmodel.FCMModel;

import java.util.ArrayList;

/**
 * Created by jimin on 2017-02-10.
 */

public class ReqSendFcm {
    ReqHeader header;
    ArrayList<FCMModel> body;

    public ReqHeader getHeader() {
        return header;
    }

    public void setHeader(ReqHeader header) {
        this.header = header;
    }

    public ArrayList<FCMModel> getBody() {
        return body;
    }

    public void setBody(ArrayList<FCMModel> body) {
        this.body = body;
    }
}
