package com.nestorledon.ezapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.nestorledon.ezapp.navigation.NavigableView;


/**
 * A fragment class for handling basic scaffolding
 * and UI interactions.
 *
 * NOTE: Keep members as inaccessible as possible for a clean API.
 * NOTE: Provide robust overloads to avoid modifications to this class.
 * TODO: Consider making members PRIVATE and require setting via constructor.
 * Created by nestorledon on 2/21/15.
 */
public abstract class FragmentBase extends Fragment implements NavigableView {

    protected String title;

    public FragmentBase() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
