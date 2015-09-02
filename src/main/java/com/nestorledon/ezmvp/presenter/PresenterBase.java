package com.nestorledon.ezmvp.presenter;

import com.nestorledon.ezmvp.EventController;

/**
 * Created by nestorledon on 5/9/15.
 */
public abstract class PresenterBase implements Presenter {

    public PresenterBase() {
        EventController.INSTANCE.register(this);
    }

}
