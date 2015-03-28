package com.lbcinternal.sensemble.activities;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;

import com.lbcinternal.sensemble.MyWebViewClient;
import com.lbcinternal.sensemble.R;

public class WebViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.getSettings().setAppCacheEnabled(true);
        myWebView.getSettings().setRenderPriority(RenderPriority.HIGH);
        myWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        String action = PreferenceManager.getDefaultSharedPreferences(myWebView.getContext())
                .getString("action", "register");
        switch (action) {
            case "login":
                myWebView.loadUrl("https://lbc-shapecroydon-ci-dev.azurewebsites.net/Account/login.aspx?ReturnUrl=%2f");
                break;
            case "register":
                myWebView.loadUrl("https://www.shapecroydon.org/Account/register.aspx?ReturnUrl=%2fviewideas.aspx");
                break;
            case "recover":
                myWebView.loadUrl("https://www.shapecroydon.org/Account/password-retrieval.aspx");
                break;
            case "logout":
                myWebView.loadUrl("https://www.shapecroydon.org/Account/login.aspx?apiLogoff=true");
                break;
        }
    }
}
