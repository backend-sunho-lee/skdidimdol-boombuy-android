package com.taca.boombuy.Resmodel;

import com.taca.boombuy.networkmodel.ReceivedOrderDTO;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-03-04.
 */

public class ResReceivedItem {

    String message;
    ArrayList<ReceivedOrderDTO> result;
    ResError error;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ReceivedOrderDTO> getResult() {
        return result;
    }

    public void setResult(ArrayList<ReceivedOrderDTO> result) {
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
        return "ResReceivedItem{" +
                "message='" + message + '\'' +
                ", result=" + result +
                ", error=" + error +
                '}';
    }
}

