package com.taca.boombuy.Resmodel;

import com.taca.boombuy.networkmodel.OrderReceivedBasicInfoDTO;
import com.taca.boombuy.networkmodel.OrderReceivedProductInfoDTO;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-03-04.
 */

public class ResReceivedItem {

    ArrayList<OrderReceivedBasicInfoDTO> settlements;
    ArrayList<OrderReceivedProductInfoDTO> carts;

    public ResReceivedItem() {
    }

    public ResReceivedItem(ArrayList<OrderReceivedBasicInfoDTO> settlements, ArrayList<OrderReceivedProductInfoDTO> carts) {
        this.settlements = settlements;
        this.carts = carts;
    }

    public ArrayList<OrderReceivedBasicInfoDTO> getSettlements() {
        return settlements;
    }

    public void setSettlements(ArrayList<OrderReceivedBasicInfoDTO> settlements) {
        this.settlements = settlements;
    }

    public ArrayList<OrderReceivedProductInfoDTO> getCarts() {
        return carts;
    }

    public void setCarts(ArrayList<OrderReceivedProductInfoDTO> carts) {
        this.carts = carts;
    }

    @Override
    public String toString() {
        return "ResReceivedItem{" +
                "settlements=" + settlements +
                ", carts=" + carts +
                '}';
    }
}

