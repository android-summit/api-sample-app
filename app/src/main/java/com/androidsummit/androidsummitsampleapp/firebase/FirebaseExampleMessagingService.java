package com.androidsummit.androidsummitsampleapp.firebase;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;

import com.androidsummit.androidsummitsampleapp.R;

/**
 * This is an example FirebaseMessagingService.  The onMessageReceived method is implemented here to allow notifications to be
 * intercepted when the app is running in teh foreground.  When the app is in the background this method will not be run, and the
 * notification will be sent to the system tray.
 *
 * Notification Setup Instructions: https://firebase.google.com/docs/notifications/android/console-audience
 */
public class FirebaseExampleMessagingService extends FirebaseMessagingService {

    private static final String TAG = FirebaseExampleMessagingService.class.getSimpleName();

    /*
    Build a custom notification here.  This method gets called when the app is in the foreground and a notification is sent.  If the app
    is in the background and receives a notification it will be displayed and clicking it will open the app.
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // if the message contains a payload, create a notification
        if (remoteMessage.getNotification() != null) {
            NotificationCompat.Builder notifyBuilder =
                new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(remoteMessage.getNotification().getTitle())
                    .setContentText(remoteMessage.getNotification().getBody());

            // display the notification to the user
            int notificationId = 001;
            NotificationManager notifyMngr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notifyMngr.notify(notificationId, notifyBuilder.build());
        }

    }
}
