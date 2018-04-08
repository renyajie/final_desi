package utils;

import android.app.Application;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import utils.BitmapCache;

/**
 * Created by Thor on 2018/3/7.
 *
 * 管理一些全局的成员变量，保证全局访问一致
 */

public class MyApplication extends Application {
    public static RequestQueue queue;
    public static BitmapCache bitmapCache;
    public static ImageLoader imageLoader;
    public static OkHttpClient okHttpClient;
    public static Gson gson, gsonForHour, gsonForAll;
    public static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(getApplicationContext());
        bitmapCache = new BitmapCache();
        imageLoader = new ImageLoader(queue, bitmapCache);
        okHttpClient = new OkHttpClient();
        gsonForHour = new GsonBuilder().setDateFormat("HH:mm:ss").create();
        gsonForAll = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        gson = new Gson();
        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
    }

    public static RequestQueue getHttpQueue() {
        return queue;
    }

    public static BitmapCache getBitmapCache() {
        return bitmapCache;
    }

    public static ImageLoader getImageLoader() {
        return imageLoader;
    }

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public static Gson getGson(int type) {
        switch (type) {
            case AppConstant.GSON_FOR_ALL:
                return gsonForAll;
            case AppConstant.GSON_FOR_HOUR:
                return gsonForHour;
        }
        return gson;
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
