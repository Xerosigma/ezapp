package com.nestorledon.ezapp;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.nestorledon.ezapp.navigation.Navigator;
import com.nestorledon.ezapp.base.PagerAdapter;
import com.nestorledon.ezapp.widgets.slidingtab.SlidingTabLayout;

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
public abstract class ActivityBase extends AppCompatActivity {

    public final static String TAG = ActivityBase.class.getCanonicalName();

    protected Toolbar mToolbar;
    protected DrawerLayout mDrawerLayout;
    protected ListView mDrawerList;
    protected ActionBarDrawerToggle mDrawerToggle;
    protected FragmentManager fragmentManager = getSupportFragmentManager();
    protected ViewPager mViewPager;
    protected SlidingTabLayout mSlidingTabLayout;
    protected boolean hideOptionsMenu = false;
    protected boolean poppingBackStack = false;

    protected final int CONTENT_FRAME = R.id.ez_Content;

    /** Object responsible for navigation, cyclic reference to child activity. */
    protected Navigator mNavigator;
    protected FragmentBase mCurrentFragment;


    protected void configureActivity(Navigator navigator) {
        this.mNavigator = navigator;
    }
    protected void configureActivity(Navigator navigator, boolean hideOptionsMenu) {
        this.mNavigator = navigator;
        this.hideOptionsMenu = hideOptionsMenu;
        View v  = findViewById(R.id.ez_Content);
        v.setVisibility(View.VISIBLE);

        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


    /**
     * Configures and enables default toolbar.
     */
    protected void setToolbar() throws ToolbarNotFoundException {
        setToolbar(null);
    }


    /**
     * Configures and enables default toolbar with logo.
     * @param logo drawable id.
     */
    protected void setToolbar(@Nullable Integer logo) throws ToolbarNotFoundException {
        setToolbar(R.id.ez_Toolbar, logo);
    }


    /**
     * Configures and enables provided toolbar with logo.
     * @param toolbarId toolbar id.
     * @param logo drawable id.
     */
    protected void setToolbar(@Nullable Integer toolbarId , @Nullable Integer logo) throws ToolbarNotFoundException {
        mToolbar = (Toolbar) findViewById(toolbarId);
        if(null != logo) { mToolbar.setLogo(logo); }

        if(null == mToolbar) {
            mToolbar = (Toolbar) findViewById(R.id.ez_Toolbar);
            if(null == mToolbar) {
                throw new ToolbarNotFoundException();
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


    /*
    protected void setGlobalPagerTabs(List<Fragment> fragments) {
        PagerAdapter pagerAdapter = new PagerAdapter(fragmentManager);

        for (Fragment f : fragments) {
            pagerAdapter.addFragment(f);
        }

        mViewPager = (ViewPager) findViewById(R.id.ez_TabViewPager);
        mViewPager.setVisibility(View.VISIBLE);
        mViewPager.setAdapter(pagerAdapter);

        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.ez_Tabs);
        mSlidingTabLayout.setVisibility(View.VISIBLE);

        // Need to hide ez_Content since viewPager is containing the fragments.
        //View v  = findViewById(R.id.ez_Content);
        //v.setVisibility(View.GONE);
    }*/

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


    public class AppBarLayoutNotFoundException extends Exception {
        public AppBarLayoutNotFoundException() {
            super("AppBarLayout not found! Have you set your activity's content view? setContentView(R.layout.main_drawer_frame);");
        }
    }


    public class ToolbarNotFoundException extends Exception {
        public ToolbarNotFoundException() {
            super("Toolbar not found! Please call setToolbar() with valid toolbar resource id.");
        }
    }


    public class CollapsibleToolbarLayoutNotFoundException extends Exception {
        public CollapsibleToolbarLayoutNotFoundException() {
            super("CollapsibleToolbarLayout not found! Have you called showCollapsibleToolbar()?");
        }
    }
}
