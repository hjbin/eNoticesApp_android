package com.twinly.enotices.enoticesapp.services;


import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.twinly.enotices.enoticesapp.activity.RegisterActivity;

/**
 * Created by huangjinbin on 2018/3/1.
 */

public class EnoticesFirebaseMessagesService extends FirebaseMessagingService {
    private static String TAG=EnoticesFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        Log.d(TAG,"[MSG From]: "+remoteMessage.getFrom());
        Log.d(TAG,"[MSG content]"+remoteMessage.getNotification().getBody());
        //openActivity();

    }

    private void openActivity() {
        Intent it=new Intent(this, RegisterActivity.class);
        startActivity(it);
    }
}
