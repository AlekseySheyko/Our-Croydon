package com.lbcinternal.sensemble.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lbcinternal.sensemble.R;
import com.lbcinternal.sensemble.activities.MainActivity;
import com.lbcinternal.sensemble.rest.ApiService;
import com.lbcinternal.sensemble.rest.RestClient;
import com.lbcinternal.sensemble.rest.model.PostCategory;
import com.lbcinternal.sensemble.views.CategoryPicker;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PostIdeaFragment extends Fragment {

    public PostIdeaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_post_idea,
                container, false);

        ApiService service = new RestClient(getActivity()).getApiService();
        service.getPostCategories(new Callback<List<PostCategory>>() {
            @Override
            public void success(List<PostCategory> postCategories, Response response) {
                CategoryPicker picker = (CategoryPicker) rootView.findViewById(R.id.picker);
                picker.setMinValue(0);
                picker.setMaxValue(postCategories.size() - 1);

                String[] categories = new String[postCategories.size()];
                for (int i = 0; i < postCategories.size(); i++) {
                    categories[i] = postCategories.get(i).getName();
                }
                picker.setDisplayedValues(categories);
                picker.setWrapSelectorWheel(false);
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });





        return rootView;
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity())
                .getSupportActionBar().setTitle("Post your Idea");
    }
}
