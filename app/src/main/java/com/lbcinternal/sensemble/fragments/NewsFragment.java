package com.lbcinternal.sensemble.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lbcinternal.sensemble.R;

public class NewsFragment extends Fragment {

    public NewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notabs, container, false);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getActionBar().setTitle("News");
    }
}
