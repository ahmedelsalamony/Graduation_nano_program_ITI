package com.example.itimobiletrack.graduation_nano_program_iti.PushNotification;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by ahmed on 3/26/2017.
 */
//singleton pattern for volley to handle network requests.
public class MyVolley {
    private static MyVolley mInstance;
    private static Context mctx;
    private RequestQueue mRequestQueue;

    private MyVolley(Context context){
        this.mctx=context;
        mRequestQueue= getRequestQueue();
    }
    public static synchronized MyVolley getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MyVolley(context);
        }
        return mInstance;
    }
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mctx.getApplicationContext());
        }
        return mRequestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
