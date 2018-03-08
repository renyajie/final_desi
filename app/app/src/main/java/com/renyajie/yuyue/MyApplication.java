package com.renyajie.yuyue;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Thor on 2018/3/7.
 */

public class MyApplication extends Application {
    public static RequestQueue queue;
    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(getApplicationContext());
    }
    public static RequestQueue getHttpQueue() {
        return queue;
    }
}
