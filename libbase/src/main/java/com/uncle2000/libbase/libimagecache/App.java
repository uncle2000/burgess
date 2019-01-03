package com.uncle2000.libbase.libimagecache;

import android.app.Application;
import android.content.Context;

/**
 * Created by 2000 on 2017/8/8.
 */

public class App extends Application {
    private static Context instance;

    public static Context getInstance() {
        return instance;
    }

    public static void setInstance(Context instance) {
        App.instance = instance;
    }

}
