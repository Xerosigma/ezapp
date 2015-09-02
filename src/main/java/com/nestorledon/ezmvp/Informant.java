package com.nestorledon.ezmvp;

/**
 * Created by nestorledon on 5/1/15.
 */
public class Informant implements Informer {

    private boolean mSuccessful;
    private String mMessage;

    public Informant(boolean mSuccessful, String mMessage) {
        this.mSuccessful = mSuccessful;
        this.mMessage = mMessage;
    }

    @Override
    public boolean isSuccessful() {
        return mSuccessful;
    }

    @Override
    public String getMessage() {
        return mMessage;
    }
}
