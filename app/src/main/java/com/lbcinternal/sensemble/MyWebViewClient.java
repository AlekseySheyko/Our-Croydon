package com.lbcinternal.sensemble;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lbcinternal.sensemble.activities.CheckEmailActivity;
import com.lbcinternal.sensemble.activities.LoginActivity;
import com.lbcinternal.sensemble.activities.MainActivity;

public class MyWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {

        String action = PreferenceManager.getDefaultSharedPreferences(webView.getContext())
                .getString("action", "register");
        if (action.equals("recover") &&
                url.equals("https://www.shapecroydon.org/Account/login.aspx")) {
            webView.getContext().startActivity(new Intent(
                    webView.getContext(), LoginActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        } else if (action.equals("register") &&
                url.equals("https://www.shapecroydon.org/Account/login.aspx")) {
            webView.getContext().startActivity(new Intent(
                    webView.getContext(), LoginActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        } else if (action.equals("login") &&
                url.equals("https://lbc-shapecroydon-ci-dev.azurewebsites.net/")) {
            webView.getContext().startActivity(new Intent(
                    webView.getContext(), MainActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        }


        if (url.contains("Account/login.aspx?userRegistered=yes")) {
            webView.getContext().startActivity(new Intent(
                    webView.getContext(), CheckEmailActivity.class
            ));
        } else {
            webView.loadUrl(url);
        }
        Log.i("TAG", "WebView override URL: " + url);
        return true;
    }

    @Override public void onPageFinished(WebView webView, String url) {
        super.onPageFinished(webView, url);
        webView.setVisibility(View.VISIBLE);

        if (url.equals("https://www.shapecroydon.org/Account/login.aspx?apiLogoff=true")) {
            webView.getContext().startActivity(new Intent(
                    webView.getContext(), LoginActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        } else if (url.equals("https://lbc-shapecroydon-ci-dev.azurewebsites.net/Account/login.aspx?apiLogoff=true")) {
            webView.getContext().startActivity(new Intent(
                    // TODO: Do not return to main activity when pressing back on logging screen after logout
                    webView.getContext(), LoginActivity.class));
        }
    }
}
