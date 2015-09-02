package com.nestorledon.ezmvp;

import android.view.View;

/**
 * Contract detailing an object that can
 * handle ui input.
 * Created by nestorledon on 2/23/15.
 */
public interface InputHandler {

    /**
     * Handles onClick events.
     * @param view the cliecked view.
     */
    void handleOnClick(View view);
}
