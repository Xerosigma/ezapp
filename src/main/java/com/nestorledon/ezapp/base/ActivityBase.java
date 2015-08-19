package com.nestorledon.ezapp.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.nestorledon.ezapp.R;
import com.nestorledon.ezapp.base.widgets.AdjustedDrawerLayout;
import com.nestorledon.ezapp.base.widgets.SlidingTabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An activity class for handling basic scaffolding
 * of toolbars, navigation drawers, transactions, and more.
 *
 * This class also handles fragment navigation without the need
 * for modification. @see Navigator
 *
 * NOTE: Keep members as inaccessible as possible for a clean API.
 * NOTE: Provide robust overloads to avoid modifications to this class.
 * Created by nestorledon on 2/21/15.
 */
public abstract class ActivityBase extends ActionBarActivity {

    protected Toolbar mToolbar;
    protected AdjustedDrawerLayout mDrawerLayout;
    protected ListView mDrawerList;
    protected ActionBarDrawerToggle mDrawerToggle;
    protected FragmentManager fragmentManager = getSupportFragmentManager();
    protected ViewPager mViewPager;
    protected SlidingTabLayout mSlidingTabLayout;
    protected boolean hideOptionsMenu = false;
    protected boolean poppingBackStack = false;

    protected final int CONTENT_FRAME = R.id.content_frame;

    /** Object responsible for navigation, cyclic reference to child activity. */
    protected Navigator mNavigator;
    protected FragmentBase mCurrentFragment;


    protected void configureActivity(Navigator navigator) {
        this.mNavigator = navigator;
    }
    protected void configureActivity(Navigator navigator, boolean hideOptionsMenu) {
        this.mNavigator = navigator;
        this.hideOptionsMenu = hideOptionsMenu;
    }

    protected void setToolbar() {
        setToolbar(null);
    }
    protected void setToolbar(Integer logo) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if(null != logo) { mToolbar.setLogo(logo); }
        mToolbar.setVisibility(View.VISIBLE);
        setSupportActionBar(mToolbar);
    }

    protected void setToolbarListener(Toolbar.OnMenuItemClickListener listener) {
        mToolbar.setOnMenuItemClickListener(listener);
    }

    protected void setNavDrawer() {
        setNavDrawer(R.string.app_name);
    }
    protected void setNavDrawer(Integer titleId) {
        mDrawerLayout = (AdjustedDrawerLayout) findViewById(R.id.drawer_frame);
        mDrawerList = (ListView) findViewById(R.id.navdrawer_items_list);

        final ArrayList<String> sectionsAL = new ArrayList<String>( Arrays.asList(mNavigator.getSections()) );

        // Set the adapter for the list view
        mDrawerList.setAdapter( new NavigatorAdapterBase( this, mNavigator, sectionsAL ));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mDrawerLayout.closeDrawers();
                final String section = sectionsAL.get(position);
                mNavigator.navigate(section, view);
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, titleId, titleId ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);

                float xPositionOpenDrawer = mDrawerList.getWidth();
                float xPositionWindowContent = ( ( slideOffset * xPositionOpenDrawer ) / 2 );
                //mCurrentFragment.setX(xPositionWindowContent);
            }
        };

        // Set the drawer toggle as the DrawerListener
        if ( mDrawerLayout != null && mDrawerToggle != null ) {
            mDrawerLayout.setDrawerListener(mDrawerToggle);
        }

        if ( getSupportActionBar() != null ) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        mDrawerToggle.syncState();
        mDrawerList.setSelector(R.drawable.ez_drawer_selector);
    }

    protected void setTabs(List<Fragment> fragments) {
        PagerAdapter pagerAdapter = new PagerAdapter(fragmentManager);

        for (Fragment f : fragments) {
            pagerAdapter.addFragment(f);
        }

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setVisibility(View.VISIBLE);
        mViewPager.setAdapter(pagerAdapter);

        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabmenu);
        mSlidingTabLayout.setVisibility(View.VISIBLE);
        mSlidingTabLayout.setViewPager(mViewPager);

        // Need to hide content_frame since viewPager is containing the fragments.
        View v  = findViewById(R.id.content_frame);
        v.setVisibility(View.GONE);
    }

    public void replaceMainFragment(final FragmentBase fragment, final boolean addToBackStack, final boolean allowStateLoss, final boolean animateWithSlide) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (fragment == null) {
                    return;
                }

                if (!addToBackStack) {
                    poppingBackStack = true;
                    fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    poppingBackStack = false;
                }

                final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                if (animateWithSlide) {
                    //fragmentTransaction.setCustomAnimations(R.anim.enter_slide_from_left, R.anim.exit_slide_from_left, R.anim.pop_enter_slide_from_left, R.anim.pop_exit_slide_from_left);
                } else {
                    //fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
                }

                if (addToBackStack) {
                    fragmentTransaction.addToBackStack(fragment.getTag());
                }

                fragmentTransaction.replace(CONTENT_FRAME, fragment, fragment.getTag());


                if (allowStateLoss) {
                    fragmentTransaction.commitAllowingStateLoss();
                } else {
                    fragmentTransaction.commit();
                }
                mCurrentFragment = fragment;
            }
        });
    }

    /**
     * Called from XML. Text/File search for
     * "onUIClick" for uses.
     * @param view
     */
    @SuppressWarnings("UnusedDeclaration")
    public void onUIClick(View view) {
        mCurrentFragment.onUIAction(view);
    }

    /**
     * Add items to ActionBar/ToolBar.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(hideOptionsMenu) { return false; }
        getMenuInflater().inflate(R.menu.ez_toolbar_main_menu, menu);
        return true;
    }

    // FIXME: Review
    public void updateSideNav() {
        final ListAdapter adapter = mDrawerList.getAdapter();
        mDrawerList.setAdapter(adapter);
    }
}
