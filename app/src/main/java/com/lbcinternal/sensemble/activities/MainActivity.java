package com.lbcinternal.sensemble.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.lbcinternal.sensemble.CroydonApp;
import com.lbcinternal.sensemble.CroydonApp.TrackerName;
import com.lbcinternal.sensemble.R;
import com.lbcinternal.sensemble.fragments.IdeasFragment;
import com.lbcinternal.sensemble.fragments.NewsFragment;
import com.lbcinternal.sensemble.fragments.OffersFragment;


public class MainActivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerToggle.syncState();
        mDrawerLayout.setDrawerListener(drawerToggle);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String section = sp.getString("section", "");
        switch (section) {
            case "news":
                ft.replace(R.id.container, new NewsFragment());
                break;
            case "offers":
                ft.replace(R.id.container, new OffersFragment());
                break;
            case "ideas":
                ft.replace(R.id.container, new IdeasFragment());
                break;
            default:
                ft.replace(R.id.container, new NewsFragment());
                break;
        }
        ft.commit();

        sendSessionInfo();
    }

    public DrawerLayout getDrawerLayout() {
        return mDrawerLayout;
    }

    private void sendSessionInfo() {
        Tracker tracker = ((CroydonApp) getApplication()).getTracker(
                TrackerName.APP_TRACKER);
        tracker.setScreenName(getString(R.string.homeScreen));
        tracker.send(new HitBuilders.AppViewBuilder().build());
    }
}
