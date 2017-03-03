package com.taca.boombuy.Resmodel;

/**
 * Created by Tacademy on 2017-03-03.
 */

public class ResSelectedSendOrder {

    ResSendOrder result;
    ResError error;

    public ResSendOrder getResult() {
        return result;
    }

    public void setResult(ResSendOrder result) {
        this.result = result;
    }

    public ResError getError() {
        return error;
    }

    public void setError(ResError error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ResSelectedSendOrder{" +
                "result=" + result +
                ", error=" + error +
                '}';
    }
}
