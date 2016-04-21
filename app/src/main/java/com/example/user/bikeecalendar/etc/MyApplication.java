package com.example.user.bikeecalendar.etc;

import android.app.Application;
import android.content.Context;

/**
 * Created by User on 2016-04-22.
 */
public class MyApplication extends Application {
    private static Context mContext;

    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getmContext() {
        return mContext;
    }
}
