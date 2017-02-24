package com.taca.boombuy.Resmodel;

import com.taca.boombuy.networkmodel.ItemDTO;

/**
 * Created by jimin on 2017-02-24.
 */


public class ResItem {

    ItemDTO result;
    String error;


    public ItemDTO getResult() {
        return result;
    }

    public void setResult(ItemDTO result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ResItem{" +
                "result=" + result +
                ", error='" + error + '\'' +
                '}';
    }
}
