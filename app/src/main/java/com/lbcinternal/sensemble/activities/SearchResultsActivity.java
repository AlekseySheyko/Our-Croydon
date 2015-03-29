package com.lbcinternal.sensemble.activities;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.lbcinternal.sensemble.R;
import com.lbcinternal.sensemble.fragments.IdeasPageFragment;

public class SearchResultsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        String query = getIntent().getStringExtra(SearchManager.QUERY);
        getSupportActionBar().setTitle("Search for: " + query);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        IdeasPageFragment fragment = new IdeasPageFragment();
        Bundle args = new Bundle();
        args.putString("query", query);
        fragment.setArguments(args);

        ft.replace(R.id.container, fragment);
        ft.commit();
    }
}
