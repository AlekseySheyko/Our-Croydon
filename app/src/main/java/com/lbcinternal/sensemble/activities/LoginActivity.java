package com.lbcinternal.sensemble.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.lbcinternal.sensemble.R;
import com.lbcinternal.sensemble.rest.ApiService;
import com.lbcinternal.sensemble.rest.CroydonClient;

import retrofit.ResponseCallback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_login);
    }

    public void signIn(View view) {
        String username = ((EditText) findViewById(R.id.usernameField)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordField)).getText().toString();

        ApiService service = new CroydonClient().getApiService();
        service.login(username, password, new ResponseCallback() {
            @Override public void success(Response response) {

            }

            @Override public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });

        startActivity(new Intent(this, MainActivity.class));
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
}
