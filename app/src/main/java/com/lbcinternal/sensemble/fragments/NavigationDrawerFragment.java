package com.lbcinternal.sensemble.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lbcinternal.sensemble.CircleTransform;
import com.lbcinternal.sensemble.R;
import com.lbcinternal.sensemble.activities.MainActivity;
import com.lbcinternal.sensemble.activities.WebViewActivity;
import com.lbcinternal.sensemble.adapters.NavigationDrawerAdapter;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;


public class NavigationDrawerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_navigation_drawer,
                container, false);

        final SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        String avatarUrl = sp.getString("avatarUrl", "");

        ImageView avatarImageView = (ImageView) rootView.findViewById(R.id.avatar);
        avatarImageView.setImageDrawable(getResources()
                .getDrawable(R.drawable.avatar_sample));

        OkHttpClient picassoClient = new OkHttpClient();

        picassoClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                String sessionKey = sp.getString("sessionId", "");

                Request newRequest = chain.request().newBuilder()
                        .addHeader("Cookie", sessionKey)
                        .build();
                return chain.proceed(newRequest);
            }
        });

        Picasso picasso = new Picasso.Builder(getActivity())
                .downloader(new OkHttpDownloader(picassoClient))
                .build();

        picasso.load(avatarUrl)
                .transform(new CircleTransform())
                .error(R.drawable.avatar_default)
                .into(avatarImageView);

        String username = sp.getString("username", "");
        TextView usernameTextView = (TextView) rootView.findViewById(R.id.username);
        usernameTextView.setText(username);

        ListView drawerList = (ListView) rootView.findViewById(
                R.id.drawer_list);
        drawerList.setAdapter(new NavigationDrawerAdapter(getActivity()));
        drawerList.setOnItemClickListener(new OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view,
                                              int position, long id) {
                if (position <= 3) {
                    selectItem(position);
                } else if (position == 6) {

                    sp.edit()
                            .putString("action", "logout")
                            .putString("rememberMe", null)
                            .putString("isSuccess", null)
                            .putString("sessionId", null)
                            .putString("username", null)
                            .putString("email", null)
                            .putString("phone", null)
                            .putString("avatarUrl", null)
                            .apply();
                    startActivity(new Intent(getActivity(), WebViewActivity.class));
                } else {
                    Toast.makeText(getActivity(), "Coming soon",
                            Toast.LENGTH_SHORT).show();
                }
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
            case 3:
                fragment = new FeedbackFragment();
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