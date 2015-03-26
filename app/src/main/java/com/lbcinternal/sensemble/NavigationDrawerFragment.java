package com.lbcinternal.sensemble;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class NavigationDrawerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_navigation_drawer,
                container, false);

        ListView drawerList = (ListView) rootView.findViewById(
                R.id.drawer_list);
        drawerList.setAdapter(new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                new String[]{"News", "Offers and events", "Shape Croygon"}
        ));

        return rootView;
    }

}