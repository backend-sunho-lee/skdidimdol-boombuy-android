package com.taca.boombuy.Resmodel;

import com.taca.boombuy.networkmodel.ResFriendDTO;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-27.
 */

public class ResFriendList {

    ArrayList<ResFriendDTO> result;
    ResError error;

    public ResFriendList() {
    }

    public ResFriendList(ArrayList<ResFriendDTO> result, ResError error) {
        this.result = result;
        this.error = error;
    }

    public ArrayList<ResFriendDTO> getResult() {
        return result;
    }

    public void setResult(ArrayList<ResFriendDTO> result) {
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
        return "ResFriendList{" +
                "result=" + result +
                ", error=" + error +
                '}';
    }
}
