package com.nestorledon.ezmvp.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nestorledon on 5/9/15.
 */
public class EventHandler<T> {

    private List events = new ArrayList();

    public EventHandler() {
    }

    public EventHandler(Delegate delegate) {
    }

    public void add(Object sender, EventArgs args) {
        events.add(new Event(sender, args));
    }
}
