package com.taca.boombuy.Resmodel;

import com.taca.boombuy.networkmodel.ItemDTO;

import java.util.ArrayList;

/**
 * Created by jimin on 2017-02-24.
 */

public class ResItems {

    ArrayList<ItemDTO> result;

    ResError error;

    public ArrayList<ItemDTO> getResult() {
        return result;
    }

    public void setResult(ArrayList<ItemDTO> result) {
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
        return "ResItems{" +
                "result=" + result +
                ", error=" + error +
                '}';
    }
}
