package com.lbcinternal.sensemble.activities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.lbcinternal.sensemble.R;
import com.microsoft.windowsazure.notifications.NotificationsHandler;

public class PushHandler extends NotificationsHandler {

    private static final int NOTIFICATION_ID = 1;
    private Context mContext;

    static public MainActivity mActivity;

    @Override
    public void onReceive(Context context, Bundle bundle) {
        mContext = context;

        String message = bundle.getString("message");
        showNotification(message);
    }

    private void showNotification(String message) {
        NotificationManager notificationManager = (NotificationManager)
                mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0,
                new Intent(mContext, MainActivity.class), 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(mContext.getString(R.string.notification_title));

        if (message != null) {
            builder.setStyle(new NotificationCompat.BigTextStyle()
                    .bigText(message))
                    .setContentText(message);
        }

        builder.setContentIntent(contentIntent);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
