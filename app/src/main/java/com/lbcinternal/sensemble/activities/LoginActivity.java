package com.lbcinternal.sensemble.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.lbcinternal.sensemble.R;
import com.lbcinternal.sensemble.rest.ApiService;
import com.lbcinternal.sensemble.rest.CroydonClient;
import com.lbcinternal.sensemble.rest.model.User;

import org.apache.http.HttpStatus;

import retrofit.Callback;
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
        if (username.isEmpty()) {
            Toast.makeText(this, "Please enter your username", Toast.LENGTH_SHORT).show();
            return;
        }

        String email;
        if (!username.contains("@croydon.gov.uk")) {
            email = username + "@croydon.gov.uk";
        } else {
            email = username;
        }
        String password = ((EditText) findViewById(R.id.passwordField)).getText().toString();
        if (password.isEmpty()) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        final boolean remember = ((ToggleButton) findViewById(R.id.toggle)).isChecked();

        ApiService service = new CroydonClient().getApiService();
        service.login(email, password, remember, new Callback<User>() {
            @Override public void success(User user, Response response) {
                if (response.getStatus() == HttpStatus.SC_OK && user.isSuccess()) {
                    if (remember) {
                        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(
                                LoginActivity.this);
                        sp.edit()
                                .putString("sessionId", user.getSessionId())
                                .putString("username", user.getUsername())
                                .putString("email", user.getEmail())
                                .putString("phone", user.getPhone())
                                .putString("avatarUrl", user.getAvatarUrl())
                                .apply();
                        Log.i("TAG", "Session ID: " + user.getSessionId());
                        Log.i("TAG", "Name: " + user.getUsername());
                        Log.i("TAG", "Email: " + user.getEmail());
                        Log.i("TAG", "Phone: " + user.getPhone());
                        Log.i("TAG", "Avatar URL: " + user.getAvatarUrl());
                    }
                    startActivity(new Intent(LoginActivity.this,
                            MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed. Check your credentials.", Toast.LENGTH_SHORT).show();
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
}
