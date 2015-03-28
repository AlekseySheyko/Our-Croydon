package com.lbcinternal.sensemble.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;

import com.lbcinternal.sensemble.R;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        startActivity(new Intent(this, WebViewActivity.class));
        PreferenceManager.getDefaultSharedPreferences(this)
                .edit().putString("action", "login").apply();
    }

    public void createAccount(View view) {
        startActivity(new Intent(this, WebViewActivity.class));
        PreferenceManager.getDefaultSharedPreferences(this)
                .edit().putString("action", "register").apply();
    }

    public void recover(View view) {
        startActivity(new Intent(this, WebViewActivity.class));
        PreferenceManager.getDefaultSharedPreferences(this)
                .edit().putString("action", "recover").apply();
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
