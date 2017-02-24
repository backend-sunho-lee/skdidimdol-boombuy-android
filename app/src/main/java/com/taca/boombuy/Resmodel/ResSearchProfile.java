package com.taca.boombuy.Resmodel;

import com.taca.boombuy.networkmodel.ProfileDTO;

/**
 * Created by jimin on 2017-02-24.
 */

public class ResSearchProfile {

    ProfileDTO result;

    String error;

    public ProfileDTO getResult() {
        return result;
    }

    public void setResult(ProfileDTO result) {
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
        return "ResSearchProfile{" +
                "result=" + result +
                ", error='" + error + '\'' +
                '}';
    }
}
