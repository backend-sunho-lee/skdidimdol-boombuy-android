package com.taca.boombuy.evt;

import com.squareup.otto.Bus;

public class OTTOBus {
    private static OTTOBus ourInstance = new OTTOBus();

    public static OTTOBus getInstance() {
        return ourInstance;
    }

    private OTTOBus() {
    }
    Bus sign_up_bus = new Bus();
    Bus sign_in_bus = new Bus();

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
}
