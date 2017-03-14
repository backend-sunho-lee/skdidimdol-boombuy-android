package com.taca.boombuy.Resmodel;

/**
 * Created by Tacademy on 2017-03-14.
 */

public class PayCancelTokenModel {

    String code;
    String message;
    PayCancelTokenInnerModel response;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PayCancelTokenInnerModel getResponse() {
        return response;
    }

    public void setResponse(PayCancelTokenInnerModel response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "PayCancelTokenModel{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", response=" + response +
                '}';
    }

}
