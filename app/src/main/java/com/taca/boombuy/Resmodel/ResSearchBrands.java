package com.taca.boombuy.Resmodel;

import com.taca.boombuy.networkmodel.BrandDTO;

import java.util.ArrayList;

/**
 * Created by jimin on 2017-02-24.
 */

public class ResSearchBrands {

    ArrayList<BrandDTO> result;
    ResError error;

    public ArrayList<BrandDTO> getResult() {
        return result;
    }

    public void setResult(ArrayList<BrandDTO> result) {
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
        return "ResSearchBrands{" +
                "result=" + result +
                ", error=" + error +
                '}';
    }
}
