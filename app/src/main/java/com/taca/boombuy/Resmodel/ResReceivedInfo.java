package com.taca.boombuy.Resmodel;

/**
 * Created by Tacademy on 2017-03-04.
 */

public class ResReceivedInfo {

    ResReceivedItem result;

    ResError error;

    public ResReceivedItem getResult() {
        return result;
    }

    public void setResult(ResReceivedItem result) {
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
        return "ResReceivedInfo{" +
                "result=" + result +
                ", error=" + error +
                '}';
    }
}
