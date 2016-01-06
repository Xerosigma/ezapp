package com.nestorledon.ezapp;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.nestorledon.ezapp.navigation.Navigator;
import com.nestorledon.ezapp.widgets.slidingtab.SlidingTabLayout;

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
public abstract class ActivityBase extends AppCompatActivity {

    public final static String TAG = ActivityBase.class.getCanonicalName();

    protected Toolbar mToolbar;
    protected TabLayout mTabLayout;
    protected NestedScrollView mNestedScrollView;
    protected FrameLayout mContentContainer;
    protected DrawerLayout mDrawerLayout;
    protected ListView mDrawerList;
    protected ActionBarDrawerToggle mDrawerToggle;
    protected ViewPager mViewPager;
    protected SlidingTabLayout mSlidingTabLayout;

    protected FragmentManager fragmentManager = getSupportFragmentManager();
    protected boolean hideOptionsMenu = false;
    protected boolean poppingBackStack = false;

    protected final int CONTENT_FRAME = R.id.ez_Content;

    /** Object responsible for navigation, cyclic reference to child activity. */
    protected Navigator mNavigator;
    protected FragmentBase mCurrentFragment;


    protected void configureActivity(Navigator navigator) {
        mNavigator = navigator;
        mNestedScrollView = (NestedScrollView) findViewById(R.id.ez_NestedScrollView);
        mContentContainer = (FrameLayout) findViewById(R.id.ez_Content);
        mViewPager = (ViewPager) findViewById(R.id.ez_TabViewPager);
    }
    protected void configureActivity(Navigator navigator, boolean hideOptionsMenu) {
        mNavigator = navigator;
        mNestedScrollView = (NestedScrollView) findViewById(R.id.ez_NestedScrollView);
        mContentContainer = (FrameLayout) findViewById(R.id.ez_Content);
        mViewPager = (ViewPager) findViewById(R.id.ez_TabViewPager);
        hideOptionsMenu = hideOptionsMenu;
        View v  = findViewById(R.id.ez_Content);
        v.setVisibility(View.VISIBLE);
    }


    /**
     * Configures and enables default toolbar.
     */
    protected void setToolbar() {
        setToolbar(null);
    }


    /**
     * Configures and enables default toolbar with logo.
     * @param logo drawable id.
     */
    protected void setToolbar(@Nullable Integer logo) {
        setToolbar(R.id.ez_Toolbar, logo);
    }


    /**
     * Configures and enables provided toolbar with logo.
     * @param toolbarId toolbar id.
     * @param logo drawable id.
     */
    protected void setToolbar(@Nullable Integer toolbarId , @Nullable Integer logo) {
        mToolbar = (Toolbar) findViewById(toolbarId);
        if(null != logo) { mToolbar.setLogo(logo); }

        if(null == mToolbar) {
            mToolbar = (Toolbar) findViewById(R.id.ez_Toolbar);
            if(null == mToolbar) {
                Log.e(TAG, "Toolbar not found! Please call setToolbar() with valid toolbar resource id.");
                return;
            }
        }

        mToolbar.setVisibility(View.VISIBLE);
        setSupportActionBar(mToolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ez_ic_ab_drawer);
    }


    protected void setToolbarListener(Toolbar.OnMenuItemClickListener listener) {
        mToolbar.setOnMenuItemClickListener(listener);
    }


    protected void setTabLayout() {
        setTabLayout(R.id.ez_TabLayout);
    }


    protected void setTabLayout(@Nullable Integer tabLayoutId) {
        mTabLayout = (TabLayout) findViewById(tabLayoutId);

        if(null == mTabLayout) {
            mTabLayout = (TabLayout) findViewById(R.id.ez_TabLayout);
            if(null == mTabLayout) {
                Log.e(TAG, "TabLayout not found! Please call setTabLayout() with valid TabLayout resource id.");
                return;
            }
        }

        // Hide content container(ez_Content) so pager is not overlapping.
        mNestedScrollView.setVisibility(View.GONE);

        mTabLayout.setVisibility(View.VISIBLE);
    }


    protected void setNavDrawer(final ListAdapter adapter) {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.ez_DrawerLayout);
        mDrawerList = (ListView) findViewById(R.id.navdrawer_items_list);

        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mDrawerLayout.closeDrawers();
                mNavigator.navigate(mDrawerList.getItemAtPosition(position), view);
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close ) {

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
