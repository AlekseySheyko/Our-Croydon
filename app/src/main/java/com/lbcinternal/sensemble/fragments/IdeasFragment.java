package com.lbcinternal.sensemble.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lbcinternal.sensemble.R;
import com.lbcinternal.sensemble.activities.MainActivity;
import com.lbcinternal.sensemble.views.SlidingTabLayout;

public class IdeasFragment extends Fragment {

    public IdeasFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View rootView = inflater.inflate(R.layout.fragment_tabs, container, false);

        ViewPager pager = (ViewPager) rootView.findViewById(R.id.pager);
        pager.setAdapter(new PagerAdapter(getChildFragmentManager()));

        SlidingTabLayout tabBar = (SlidingTabLayout) rootView.findViewById(R.id.sliding_tabs);
        tabBar.setDistributeEvenly(true);
        tabBar.setViewPager(pager);
        tabBar.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tab_indicator);
            }
        });

        return rootView;
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity())
                .getSupportActionBar().setTitle("Ideas");
    }

    private class PagerAdapter extends FragmentPagerAdapter {

        private static final int NUM_ITEMS = 2;
        private String[] mTabTitles;

        public PagerAdapter(FragmentManager fm) {
            super(fm);

            mTabTitles = new String[]{
                    "Most recent".toUpperCase(),
                    "Highest rated".toUpperCase()
            };
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            Bundle args = new Bundle();
            if (position == 0) {
                args.putInt("order", 0);
            } else if (position == 1) {
                args.putInt("order", 2);
            }
            IdeasPageFragment fragment = new IdeasPageFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];
        }
    }

    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager =
                (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getActivity().getComponentName()));
    }
}
