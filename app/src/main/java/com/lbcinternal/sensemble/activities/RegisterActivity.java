package com.lbcinternal.sensemble.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;

import com.lbcinternal.sensemble.MyWebViewClient;
import com.lbcinternal.sensemble.R;

public class RegisterActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new MyWebViewClient());
        myWebView.loadUrl("https://www.shapecroydon.org/Account/register.aspx?ReturnUrl=%2fviewideas.aspx");
    }
}
