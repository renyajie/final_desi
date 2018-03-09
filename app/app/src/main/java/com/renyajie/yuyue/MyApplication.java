package com.renyajie.yuyue;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import main.helper.BitmapCache;

/**
 * Created by Thor on 2018/3/7.
 *
 * 管理一些全局的成员变量，保证全局访问一致
 */

public class MyApplication extends Application {
    public static RequestQueue queue;
    public static BitmapCache bitmapCache;

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(getApplicationContext());
        bitmapCache = new BitmapCache();
    }

    public static RequestQueue getHttpQueue() {
        return queue;
    }

    public static BitmapCache getBitmapCache() {
        return bitmapCache;
    }
}
