package com.taca.boombuy.networkmodel;

/**
 * Created by Tacademy on 2017-03-03.
 */

public class SendOrdersDTO {

    int oid;
    String state;
    String orderstime;
    String receiver;
    String receiverphoto;
    String sender;
    String senderphoto;
    int cnt;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOrderstime() {
        return orderstime;
    }

    public void setOrderstime(String orderstime) {
        this.orderstime = orderstime;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverphoto() {
        return receiverphoto;
    }

    public void setReceiverphoto(String receiverphoto) {
        this.receiverphoto = receiverphoto;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderphoto() {
        return senderphoto;
    }

    public void setSenderphoto(String senderphoto) {
        this.senderphoto = senderphoto;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    @Override
    public String toString() {
        return "SendOrdersDTO{" +
                "oid=" + oid +
                ", state='" + state + '\'' +
                ", orderstime='" + orderstime + '\'' +
                ", receiver='" + receiver + '\'' +
                ", receiverphoto='" + receiverphoto + '\'' +
                ", sender='" + sender + '\'' +
                ", senderphoto='" + senderphoto + '\'' +
                ", cnt=" + cnt +
                '}';
    }
}


