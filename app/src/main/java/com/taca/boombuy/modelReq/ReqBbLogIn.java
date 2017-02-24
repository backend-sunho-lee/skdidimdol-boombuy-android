package com.taca.boombuy.modelReq;

import com.taca.boombuy.Reqmodel.ReqHeader;
import com.taca.boombuy.netmodel.LonInModel;

/**
 * Created by jimin on 2017-02-13.
 */

public class ReqBbLogIn {
    ReqHeader header;
    LonInModel body;

    public ReqHeader getHeader() {
        return header;
    }

    public void setHeader(ReqHeader header) {
        this.header = header;
    }

    public LonInModel getBody() {
        return body;
    }

    public void setBody(LonInModel body) {
        this.body = body;
    }
}
