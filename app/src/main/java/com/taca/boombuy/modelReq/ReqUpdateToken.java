package com.taca.boombuy.modelReq;

import com.taca.boombuy.Reqmodel.ReqHeader;
import com.taca.boombuy.netmodel.UpdateTokenModel;

/**
 * Created by jimin on 2017-02-13.
 */

public class ReqUpdateToken {
    ReqHeader header;
    UpdateTokenModel body;

    public ReqHeader getHeader() {
        return header;
    }

    public void setHeader(ReqHeader header) {
        this.header = header;
    }

    public UpdateTokenModel getBody() {
        return body;
    }

    public void setBody(UpdateTokenModel body) {
        this.body = body;
    }
}
