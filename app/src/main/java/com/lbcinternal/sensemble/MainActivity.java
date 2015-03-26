package com.lbcinternal.sensemble;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.lbcinternal.sensemble.fragments.NewsFragment;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container, new NewsFragment());
        ft.commit();
    }
}
