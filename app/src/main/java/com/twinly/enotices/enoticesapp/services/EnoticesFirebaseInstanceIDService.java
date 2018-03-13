package com.twinly.enotices.enoticesapp.services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by huangjinbin on 2018/3/1.
 */

public class EnoticesFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static String TAG=EnoticesFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onTokenRefresh(){
        String refreshToken= FirebaseInstanceId.getInstance().getToken();

        Log.i(TAG,"[DeviceToken]: "+refreshToken);
    }

}
