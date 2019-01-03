package com.uncle2000.libbase.libutils;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * Created by 2000 on 2017/8/8.
 */

public class App extends MultiDexApplication {
    private static Context instance;

    public static Context getInstance() {
        return instance;
    }

    public static void setInstance(Context instance) {
        App.instance = instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SharedValueUtils.INSTANCE.init(this);
    }
}
