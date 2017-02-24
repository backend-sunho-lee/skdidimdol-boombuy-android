package com.taca.boombuy.Resmodel;

import com.taca.boombuy.networkmodel.ItemDTO;

/**
 * Created by jimin on 2017-02-24.
 */


public class ResItemDetail {

    ItemDTO result;
    ResError error;


    public ItemDTO getResult() {
        return result;
    }

    public void setResult(ItemDTO result) {
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
        return "ResItemDetail{" +
                "result=" + result +
                ", error=" + error +
                '}';
    }
}
