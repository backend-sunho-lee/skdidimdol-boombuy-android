package com.taca.boombuy.evt;

import com.squareup.otto.Bus;

public class OTTOBus {
    private static OTTOBus ourInstance = new OTTOBus();

    public static OTTOBus getInstance() {
        return ourInstance;
    }

    private OTTOBus() {
    }
    Bus bus = new Bus();

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
}
