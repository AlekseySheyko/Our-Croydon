package com.lbcinternal.sensemble.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lbcinternal.sensemble.R;
import com.lbcinternal.sensemble.activities.MainActivity;

public class PostIdeaFragment extends Fragment {

    public PostIdeaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_post_idea,
                container, false);

        EditText titleEditText = (EditText) rootView.findViewById(R.id.title);

        return rootView;
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity())
                .getSupportActionBar().setTitle("Post your Idea");
    }
}
