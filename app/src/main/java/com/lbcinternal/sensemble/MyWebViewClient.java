package com.lbcinternal.sensemble;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lbcinternal.sensemble.activities.CheckEmailActivity;
import com.lbcinternal.sensemble.activities.LoginActivity;

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
        }


        if (url.contains("Account/login.aspx?userRegistered=yes")) {
            webView.getContext().startActivity(new Intent(
                    webView.getContext(), CheckEmailActivity.class
            ));
        } else {
            webView.loadUrl(url);
        }
        Log.i("TAG", "Url: " + url);
        return true;
    }

    @Override public void onPageFinished(WebView webView, String url) {
        super.onPageFinished(webView, url);
        webView.setVisibility(View.VISIBLE);
    }
}
