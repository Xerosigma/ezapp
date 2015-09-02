package com.nestorledon.ezmvp.event;

/**
 * An interface representing a callback.
 *
 * Created by nestorledon on 5/9/15.
 */
public interface Delegate {

    void execute(Object origin, EventArgs args);

}
