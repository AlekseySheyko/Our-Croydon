package com.lbcinternal.sensemble.fragments;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.lbcinternal.sensemble.R;
import com.lbcinternal.sensemble.activities.MainActivity;
import com.lbcinternal.sensemble.rest.ApiService;
import com.lbcinternal.sensemble.rest.RestClient;
import com.lbcinternal.sensemble.rest.model.Idea;
import com.lbcinternal.sensemble.rest.model.IdeaCategory;
import com.lbcinternal.sensemble.views.CategoryPicker;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PostIdeaFragment extends Fragment {

    private String LOG_TAG = PostIdeaFragment.class.getSimpleName();

    private List<IdeaCategory> mCategories;

    public PostIdeaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_post_idea,
                container, false);

        final CategoryPicker picker = (CategoryPicker) rootView.findViewById(R.id.picker);
        setDividerColor(picker, getResources().getColor(R.color.picker_divider));

        final ApiService service = new RestClient(getActivity()).getApiService();
        service.getPostCategories(new Callback<List<IdeaCategory>>() {
            @Override
            public void success(List<IdeaCategory> postCategories, Response response) {
                mCategories = postCategories;

                picker.setMinValue(0);
                picker.setMaxValue(postCategories.size() - 1);

                String[] categories = new String[postCategories.size()];
                for (int i = 0; i < postCategories.size(); i++) {
                    categories[i] = postCategories.get(i).getName();

                    if (postCategories.get(i).isChecked()) {
                        picker.setValue(i);
                    }
                }
                if (picker.getValue() == 0) {
                    picker.setValue(1);
                }

                picker.setDisplayedValues(categories);
                picker.setWrapSelectorWheel(false);
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });

        Button submitButton = (Button) rootView.findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = ((EditText) rootView.findViewById(R.id.title)).getText().toString();
                String body = ((EditText) rootView.findViewById(R.id.body)).getText().toString();
                IdeaCategory category = mCategories.get(picker.getValue());
                boolean isAnonymous = ((CheckBox) rootView.findViewById(R.id.anonymous)).isChecked();

                if (title.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter a title", Toast.LENGTH_SHORT).show();
                    return;
                } else if (body.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter your idea", Toast.LENGTH_SHORT).show();
                    return;
                }

                service.postIdea(title, body, title, category, new String[]{}, true, true, isAnonymous, new Callback<Idea>() {
                    @Override
                    public void success(Idea idea, Response response) {
                        
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        error.printStackTrace();
                    }
                });
                Toast.makeText(getActivity(), "Thank you for your idea", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });



        return rootView;
    }

    private void setDividerColor(NumberPicker picker, int color) {

        java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    ColorDrawable colorDrawable = new ColorDrawable(color);
                    pf.set(picker, colorDrawable);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity())
                .getSupportActionBar().setTitle("Post your Idea");
    }
}
