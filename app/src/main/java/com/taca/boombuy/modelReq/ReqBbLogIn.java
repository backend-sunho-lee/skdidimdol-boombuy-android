package com.taca.boombuy.modelReq;

import com.taca.boombuy.Reqmodel.ReqHeader;
import com.taca.boombuy.netmodel.LoginModel;

/**
 * Created by jimin on 2017-02-13.
 */

public class ReqBbLogIn {
    ReqHeader header;
    LoginModel body;

    public ReqHeader getHeader() {
        return header;
    }

    public void setHeader(ReqHeader header) {
        this.header = header;
    }

    public LoginModel getBody() {
        return body;
    }

    public void setBody(LoginModel body) {
        this.body = body;
    }
}
