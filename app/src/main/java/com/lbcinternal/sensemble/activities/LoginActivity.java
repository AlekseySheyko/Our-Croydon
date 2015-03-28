package com.lbcinternal.sensemble.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.lbcinternal.sensemble.R;
import com.lbcinternal.sensemble.rest.ApiService;
import com.lbcinternal.sensemble.rest.CroydonClient;

import java.io.IOException;
import java.io.InputStream;

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
        boolean remember = ((ToggleButton) findViewById(R.id.toggle)).isChecked();

        ApiService service = new CroydonClient().getApiService();
        service.login(username, password, remember, new ResponseCallback() {
            @Override public void success(Response response) {
                try {
                    InputStream is = response.getBody().in();
                    String responseString = convertStreamToString(is);
                    if (responseString.contains("Please log in using your @croydon.gov.uk email address.")) {
                        Toast.makeText(LoginActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override public void failure(RetrofitError error) {
                Toast.makeText(LoginActivity.this, "Login failed. Check your credentials.", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });
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
