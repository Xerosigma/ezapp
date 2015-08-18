package com.nestorledon.ezapp.base.presenter;

import android.view.View;

import com.nestorledon.ezapp.base.Informer;

/**
 * Created by nestorledon on 4/24/15.
 */
public interface Presenter {

    void onClick(View v);

    Informer validate(int id, Object data);
}
