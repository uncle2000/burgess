package com.uncle2000.libbase;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.scwang.smartrefresh.header.WaterDropHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

/**
 * Created by 2000 on 2017/8/8.
 */

public class App extends MultiDexApplication {
    private static Context instance;

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(android.R.color.white, android.R.color.black);//全局设置主题颜色
            return new WaterDropHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }

    public static Context getInstance() {
        return instance;
    }

    public static void setInstance(Context instance) {
        App.instance = instance;
    }

}
