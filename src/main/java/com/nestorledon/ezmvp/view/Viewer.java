package com.nestorledon.ezmvp.view;

import android.app.Activity;
import android.util.SparseArray;

import com.nestorledon.ezmvp.presenter.Presenter;

/**
 * Created by nestorledon on 4/30/15.
 */
public interface Viewer {

    void showMessage(String message, String action, Object listener);

    SparseArray<Object> getData();

    void setViewData(SparseArray<Object> data);

    Activity getActivity();

    Presenter getPresenter();
}
