package com.nestorledon.ezapp.base;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

/**
 * A com.nestorledon.ezapp.base fragment class for handling basic scaffolding
 * and UI interactions.
 *
 * NOTE: Keep members as inaccessible as possible for a clean API.
 * NOTE: Provide robust overloads to avoid modifications to this class.
 * TODO: Consider making members PRIVATE and require setting via constructor.
 * Created by nestorledon on 2/21/15.
 */
public abstract class FragmentBase extends Fragment {

    protected InputHandler inputHandler;
    protected String title;

    public FragmentBase() {
        // Required empty public constructor
    }

    public String getTitle() {
        return title;
    }

    public void onUIAction(View v) {
        inputHandler.handleOnClick(v);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
