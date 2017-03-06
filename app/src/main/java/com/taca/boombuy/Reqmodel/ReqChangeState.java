package com.taca.boombuy.Reqmodel;

/**
 * Created by jimin on 2017-03-06.
 */

public class ReqChangeState {
    int oid;

    @Override
    public String toString() {
        return "ReqChangeState{" +
                "oid=" + oid +
                '}';
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public ReqChangeState(int oid) {

        this.oid = oid;
    }

    public ReqChangeState() {

    }
}
