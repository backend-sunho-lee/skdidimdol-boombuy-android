package com.taca.boombuy.networkmodel;

import com.taca.boombuy.Resmodel.ResError;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-03-03.
 */

public class ResSimpleSendOrders {

    ArrayList<SimpleSendOrdersDTO> result;
    ResError error;

    public ArrayList<SimpleSendOrdersDTO> getResult() {
        return result;
    }

    public void setResult(ArrayList<SimpleSendOrdersDTO> result) {
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
        return "ResSimpleSendOrders{" +
                "result=" + result +
                ", error=" + error +
                '}';
    }
}
