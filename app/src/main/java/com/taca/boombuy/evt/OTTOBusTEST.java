package com.taca.boombuy.evt;

import com.squareup.otto.Bus;

public class OTTOBusTEST {
    private static OTTOBusTEST ourInstance = new OTTOBusTEST();

    public static OTTOBusTEST getInstance() {
        return ourInstance;
    }

    private OTTOBusTEST() {
    }
    Bus sign_up_bus = new Bus();
    Bus sign_in_bus = new Bus();

    Bus search_items_bus = new Bus();
    Bus search_brands_bus = new Bus();
    Bus selected_item_detail_bus = new Bus();
    Bus search_items_coupon_bus = new Bus();

    public Bus getSearch_items_coupon_bus() {
        return search_items_coupon_bus;
    }

    public void setSearch_items_coupon_bus(Bus search_items_coupon_bus) {
        this.search_items_coupon_bus = search_items_coupon_bus;
    }

    public Bus getSelected_item_detail_bus() {
        return selected_item_detail_bus;
    }

    public void setSelected_item_detail_bus(Bus selected_item_detail_bus) {
        this.selected_item_detail_bus = selected_item_detail_bus;
    }

    public Bus getSign_up_bus() {
        return sign_up_bus;
    }

    public void setSign_up_bus(Bus sign_up_bus) {
        this.sign_up_bus = sign_up_bus;
    }

    public Bus getSign_in_bus() {
        return sign_in_bus;
    }

    public void setSign_in_bus(Bus sign_in_bus) {
        this.sign_in_bus = sign_in_bus;
    }

    public Bus getSearch_items_bus() {
        return search_items_bus;
    }

    public void setSearch_items_bus(Bus search_items_bus) {
        this.search_items_bus = search_items_bus;
    }

    public Bus getSearch_brands_bus() {
        return search_brands_bus;
    }

    public void setSearch_brands_bus(Bus search_brands_bus) {
        this.search_brands_bus = search_brands_bus;
    }
}
