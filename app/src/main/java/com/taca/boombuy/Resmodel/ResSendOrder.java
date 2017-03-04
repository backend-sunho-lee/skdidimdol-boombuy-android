package com.taca.boombuy.Resmodel;

import com.taca.boombuy.networkmodel.SendCartsDTO;
import com.taca.boombuy.networkmodel.SendOrdersDTO;
import com.taca.boombuy.networkmodel.SendSettlementsDTO;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-03-03.
 */

public class ResSendOrder {

    SendOrdersDTO orders;
    ArrayList<SendSettlementsDTO> settlements;
    ArrayList<SendCartsDTO> carts;


    public SendOrdersDTO getOrders() {
        return orders;
    }

    public void setOrders(SendOrdersDTO orders) {
        this.orders = orders;
    }

    public ArrayList<SendSettlementsDTO> getSettlements() {
        return settlements;
    }

    public void setSettlements(ArrayList<SendSettlementsDTO> settlements) {
        this.settlements = settlements;
    }

    public ArrayList<SendCartsDTO> getCarts() {
        return carts;
    }

    public void setCarts(ArrayList<SendCartsDTO> carts) {
        this.carts = carts;
    }


    @Override
    public String toString() {
        return "ResSendOrder{" +
                "orders=" + orders +
                ", settlements=" + settlements +
                ", carts=" + carts +
                '}';
    }
}
