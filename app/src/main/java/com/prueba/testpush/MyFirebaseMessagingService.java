package com.prueba.testpush;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d(TAG, "onNewToken: " + token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "Mensaje recibido");
        Map<String, String> data = remoteMessage.getData();
        if (data.size() > 0) {
            Log.d(TAG, "data: " + data);
            String title = data.get("titulo");
            String msg = data.get("mensaje");
            sendNotification(title, msg);

        } else{
            RemoteMessage.Notification notification = remoteMessage.getNotification();
            String title = notification.getTitle();
            String msg = notification.getBody();

            sendNotification(title, msg);
        }

    }

    private void sendNotification(String title, String msg) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, MyNotification.NOTIFICATION_ID, intent, PendingIntent.FLAG_ONE_SHOT);

        MyNotification notification = new MyNotification(this, MyNotification.CHANNEL_ID_NOTIFICATIONS);
        notification.build(R.drawable.ic_launcher_foreground, title, msg, pendingIntent);
        notification.addChannel("Notificaciones", NotificationManager.IMPORTANCE_DEFAULT);
        notification.createChannelGroup(MyNotification.CHANNEL_GROUP_GENERAL, R.string.notification_channel_group_general);
        notification.show(MyNotification.NOTIFICATION_ID);
    }
}
