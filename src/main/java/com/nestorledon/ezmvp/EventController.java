package com.nestorledon.ezmvp;

import com.squareup.otto.Bus;

/**
 * Created by nestorledon on 5/10/15.
 */
public enum EventController  {

    INSTANCE;

    private final Bus mBus = new Bus();

    public Bus getBus() {
        return mBus;
    }

    public void notify(Object event) {
        mBus.post(event);
    }

    public void register(Object observer) {
        mBus.register(observer);
    }
}
