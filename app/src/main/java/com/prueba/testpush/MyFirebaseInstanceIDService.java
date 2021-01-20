package com.prueba.testpush;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService {

    private static final String TAG = "PUSH";

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

        String token = FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG,"Token: " + token);

        enviarTokenAlSerivdor(token);

    }

    private void enviarTokenAlSerivdor(String token) {

    }
}
