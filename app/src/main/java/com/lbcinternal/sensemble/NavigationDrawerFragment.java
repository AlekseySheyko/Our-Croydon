package com.lbcinternal.sensemble;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.lbcinternal.sensemble.fragments.IdeasFragment;
import com.lbcinternal.sensemble.fragments.NewsFragment;
import com.lbcinternal.sensemble.fragments.OffersFragment;


public class NavigationDrawerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_navigation_drawer,
                container, false);

        ListView drawerList = (ListView) rootView.findViewById(
                R.id.drawer_list);
        drawerList.setAdapter(new NavigationDrawerAdapter(getActivity()));
        drawerList.setOnItemClickListener(new OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view,
                                              int position, long id) {
                selectItem(position);
            }
        });

        return rootView;
    }

    private void selectItem(int position) {
        FrameLayout container = (FrameLayout) getActivity()
                .findViewById(R.id.container);
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new NewsFragment();
                break;
            case 1:
                fragment = new OffersFragment();
                break;
            case 2:
                fragment = new IdeasFragment();
                break;
        }
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(container.getId(), fragment);
        ft.commit();

        DrawerLayout drawerLayout = ((MainActivity) getActivity())
                .getDrawerLayout();
        drawerLayout.closeDrawer(Gravity.START);
    }

}