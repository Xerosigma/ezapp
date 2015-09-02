package com.nestorledon.ezmvp.event;

import java.util.HashMap;

/**
 * Created by nestorledon on 5/9/15.
 */
public class PresenterArgs implements EventArgs {

    private HashMap<String, Object> args = new HashMap<>();

    public PresenterArgs(HashMap<String, Object> args) {
        this.args = args;
    }

    public PresenterArgs(String eventKey, Object value) {
        args.put(eventKey, value);
    }

    @Override
    public HashMap getArgs() {
        return args;
    }

    @Override
    public void setArgs(HashMap args) {
        this.args = args;
    }
}
