package com.nestorledon.ezapp.base.presenter;

import com.nestorledon.ezapp.EventController;

/**
 * Created by nestorledon on 5/9/15.
 */
public abstract class PresenterBase implements Presenter {

    public PresenterBase() {
        EventController.INSTANCE.register(this);
    }

}
