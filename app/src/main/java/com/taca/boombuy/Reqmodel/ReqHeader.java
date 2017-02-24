package com.taca.boombuy.Reqmodel;

/**
 * Created by Tacademy on 2017-01-17.
 */

public class ReqHeader
{
    String code;

    public ReqHeader(){}

    public ReqHeader(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
