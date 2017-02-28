package com.taca.boombuy.Resmodel;

import com.taca.boombuy.networkmodel.ResMyProfileDTO;

/**
 * Created by jimin on 2017-02-28.
 */

public class ResMyProfile {
    ResMyProfileDTO result;
    ResError error;

    @Override
    public String toString() {
        return "ResMyProfile{" +
                "result=" + result +
                ", error=" + error +
                '}';
    }

    public ResMyProfileDTO getResult() {
        return result;
    }

    public void setResult(ResMyProfileDTO result) {
        this.result = result;
    }

    public ResError getError() {
        return error;
    }

    public void setError(ResError error) {
        this.error = error;
    }

    public ResMyProfile(ResMyProfileDTO result, ResError error) {

        this.result = result;
        this.error = error;
    }

    public ResMyProfile() {

    }
}
