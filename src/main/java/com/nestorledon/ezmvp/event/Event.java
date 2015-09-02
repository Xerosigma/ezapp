package com.nestorledon.ezmvp.event;

/**
 * Created by nestorledon on 5/9/15.
 */
public class Event {

    private Object sender;
    private EventArgs args;

    public Event(Object sender, EventArgs args) {
        this.sender = sender;
        this.args = args;
    }

    public Object getSender() {
        return sender;
    }

    public void setSender(Object sender) {
        this.sender = sender;
    }

    public EventArgs getArgs() {
        return args;
    }

    public void setArgs(EventArgs args) {
        this.args = args;
    }

}
