package com.lbcinternal.sensemble;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lbcinternal.sensemble.activities.CheckEmailActivity;

public class MyWebViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
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
