package com.lbcinternal.sensemble.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lbcinternal.sensemble.FeedListAdapter;
import com.lbcinternal.sensemble.MainActivity;
import com.lbcinternal.sensemble.R;
import com.lbcinternal.sensemble.rest.ApiService;
import com.lbcinternal.sensemble.rest.FeedEntry;
import com.lbcinternal.sensemble.rest.FeedXmlParser;
import com.lbcinternal.sensemble.rest.RestClient;

import java.io.InputStream;
import java.util.List;

import retrofit.ResponseCallback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class NewsFragment extends Fragment {

    public NewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_no_tabs, container, false);

        final ListView feedListView = (ListView) rootView.findViewById(R.id.list_view);

        ApiService service = new RestClient().getApiService();
        service.getNews(new ResponseCallback() {
            @Override public void success(Response response) {
                List<FeedEntry> entries = null;
                try {
                    InputStream in = response.getBody().in();
                    FeedXmlParser feedXmlParser = new FeedXmlParser();
                    entries = feedXmlParser.parse(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                feedListView.setAdapter(new FeedListAdapter(getActivity(),
                        entries));
            }

            @Override public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
        return rootView;
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity())
                .getSupportActionBar().setTitle("News");
    }
}
