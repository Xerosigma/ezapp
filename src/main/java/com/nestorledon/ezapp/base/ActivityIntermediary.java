package com.nestorledon.ezapp.base;

import com.nestorledon.ezapp.base.ActivityBase;
import com.nestorledon.ezapp.base.Navigator;

/**
 * This class is an example of an intermediary, sharing
 * some logic across a multitude of activities.
 *
 * In this case, we're sharing navigation logic since
 * the navigation drawers functionality and items
 * remain the same across activities.
 *
 * Created by nestorledon on 2/24/15.
 */
public abstract class ActivityIntermediary extends ActivityBase implements Navigator {

    private final static String TAG = "ActivityIntermediary";
}
