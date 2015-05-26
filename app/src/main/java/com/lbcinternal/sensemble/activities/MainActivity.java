package com.lbcinternal.sensemble.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.lbcinternal.sensemble.CroydonApp;
import com.lbcinternal.sensemble.CroydonApp.TrackerName;
import com.lbcinternal.sensemble.R;
import com.lbcinternal.sensemble.fragments.IdeasFragment;
import com.lbcinternal.sensemble.fragments.NewsFragment;
import com.lbcinternal.sensemble.fragments.OffersFragment;
import com.microsoft.windowsazure.messaging.NotificationHub;
import com.microsoft.windowsazure.notifications.NotificationsManager;


public class MainActivity extends ActionBarActivity {

    private String SENDER_ID = "479987947436";
    private GoogleCloudMessaging gcm;
    private NotificationHub hub;
    private String HubName = "Ourcroydonmobileapphub";
    private String HubListenConnectionString = "Endpoint=sb://ourcroydonmobileapphub-ns.servicebus.windows.net/;SharedAccessKeyName=DefaultFullSharedAccessSignature;SharedAccessKey=XjbgOY/enhnhs13Q4KXA3xUAQAIOQEOzner8s68V3V4=";

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PushHandler.mActivity = this;
        NotificationsManager.handleNotifications(this, SENDER_ID, PushHandler.class);
        gcm = GoogleCloudMessaging.getInstance(this);
        hub = new NotificationHub(HubName, HubListenConnectionString, this);
        registerWithNotificationHubs();

        if (!isOnline()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Network lost");
            builder.setMessage("An internet connection is required to use Our Croydon. Please check your connection and try again.");
            builder.setIcon(R.drawable.ic_action_airplane_mode_on);
            // Add the buttons
            builder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                    startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                    finish();
                }
            });
            builder.setNegativeButton("Finish", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User confirm exit
                    finish();
                }
            });
            // Create the AlertDialog
            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerToggle.syncState();
        mDrawerLayout.setDrawerListener(drawerToggle);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String section = sp.getString("section", "");
        switch (section) {
            case "news":
                ft.replace(R.id.container, new NewsFragment());
                break;
            case "offers":
                ft.replace(R.id.container, new OffersFragment());
                break;
            case "ideas":
                ft.replace(R.id.container, new IdeasFragment());
                break;
            default:
                ft.replace(R.id.container, new NewsFragment());
                break;
        }

        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPrefs.getBoolean("showIdeas", false)) {
            ft.replace(R.id.container, new IdeasFragment());
            sharedPrefs.edit().putBoolean("showIdeas", false).apply();
        }

        ft.commit();

        sendSessionInfo();
    }

    @SuppressWarnings("unchecked")
    private void registerWithNotificationHubs() {
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... params) {
                try {
                    String regid = gcm.register(SENDER_ID);
                    DialogNotify("Registered Successfully", "RegId : " +
                            hub.register(regid).getRegistrationId());
                } catch (Exception e) {
                    DialogNotify("Exception", e.getMessage());
                    return e;
                }
                return null;
            }
        }.execute(null, null, null);
    }

    /**
     * A modal AlertDialog for displaying a message on the UI thread
     * when theres an exception or message to report.
     *
     * @param title   Title for the AlertDialog box.
     * @param message The message displayed for the AlertDialog box.
     */
    public void DialogNotify(final String title, final String message) {
        Log.d("MainActivity", message);
    }

    public DrawerLayout getDrawerLayout() {
        return mDrawerLayout;
    }

    private void sendSessionInfo() {
        Tracker tracker = ((CroydonApp) getApplication()).getTracker(
                TrackerName.APP_TRACKER);
        tracker.setScreenName(getString(R.string.homeScreen));
        tracker.send(new HitBuilders.AppViewBuilder().build());
    }

    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
