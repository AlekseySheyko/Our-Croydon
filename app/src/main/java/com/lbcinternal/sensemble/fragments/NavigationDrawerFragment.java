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
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String sessionId = sp.getString("sessionId", "");

                Request newRequest = chain.request().newBuilder()
                        .addHeader("Cookie", "ARRAffinity=4a6693a5405e04c54f09470ae89e2b2e1cc413e698c14d2bdbefbc7dbcd395a1; ASP.NET_SessionId=" +
                                sessionId + "; .AUXBLOGENGINE=6BC6A0514DC81CF07BFBA808E66B936D1E9349852395270B418E1A2C2ECC58D7D936FD9BCF71CBF70AF2FFB0173F6FCD604D3725DA0D3C100A27D43240AB13D3077E650B6A50680E0A8A7C9AEDD4291EABB0A0FDF524036575C71E45C47009CCEEFF0705FB4541E7AE08BFB3FBA45DA9F0121E5878AB247920EEF19674E1D83BFD5BB25210C772B53796041451831C89BCF86FC1D602EC58935E157FE2796293BADEC6C941C62D054F41108DCE32AB300999B09E87C488F8F56A67F52493808CA57993B185A0013A08B58605BBB50BF950B6000A298A58D76DFC302CD1E36A5FF142FC3A7EE1783602FEE0E3B9525DB8CF292098F49C152A432830185713E3DC3936020D296F2121368D7E0A545A8BB0B89F20E4C81F6515974A749A8D8F4BD12D05FF5B041EB1DE98450C0076573C654637736393AE17B56103F8C88797EB1CB985B22D0A520F9D3D3CA1DDCC55D6EB79FF5E689372E5777FC97DF22DE288C6F916D4286E9808113039786766265D5DA2DC7F7A775DE5D99B9D8B4A1BC3DDC51FEF887BE1C3E92DE17F6341BFBF5A737EDC00BA; ai_user=C4EDCB2D-284F-4E71-B907-4CACD76B5D70; ai_session=704841FB-305B-417D-A613-FB24084148A7|2015-03-28T21:52:22.052Z|2015-03-28T21:52:24.934Z")                .build();
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
                if (position <= 2) {
                    selectItem(position);
                } else if (position == 5) {

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