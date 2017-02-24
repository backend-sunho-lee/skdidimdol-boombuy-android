package com.taca.boombuy.Resmodel;

import com.taca.boombuy.networkmodel.BrandDTO;

import java.util.ArrayList;

/**
 * Created by jimin on 2017-02-24.
 */

public class ResTotalBrands {

    ArrayList<BrandDTO> result;
    String error;

    public ArrayList<BrandDTO> getResult() {
        return result;
    }

    public void setResult(ArrayList<BrandDTO> result) {
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
        return "ResTotalBrands{" +
                "result=" + result +
                ", error='" + error + '\'' +
                '}';
    }
}
