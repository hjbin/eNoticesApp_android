package com.twinly.enotices.enoticesapp.utils.network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by jbhuang on 1/24/2018.
 */

public class RequestQueueUtils {
    private static RequestQueueUtils mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private RequestQueueUtils(Context context){
        mCtx=context;
        mRequestQueue=getRequestQueue();
    }

    public static synchronized RequestQueueUtils getInstance(Context context){
        if (mInstance==null){
            mInstance=new RequestQueueUtils(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if (this.mRequestQueue==null){
            mRequestQueue= Volley.newRequestQueue(mCtx);
        }
        return this.mRequestQueue;
    }
}
