//package com.uncle2000.lib
//
//import android.content.Context
//import android.support.multidex.MultiDexApplication
//
//import com.scwang.smartrefresh.header.WaterDropHeader
//import com.scwang.smartrefresh.layout.SmartRefreshLayout
//import com.scwang.smartrefresh.layout.footer.ClassicsFooter
//
///**
// * Created by 2000 on 2017/8/8.
// */
//
//object App : MultiDexApplication() {
//    var instance: Context? = null
//
//    init {
//        //设置全局的Header构建器
//        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
//            layout.setPrimaryColorsId(android.R.color.white, android.R.color.black)//全局设置主题颜色
//            WaterDropHeader(context)//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
//        }
//        //设置全局的Footer构建器
//        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
//            //指定为经典Footer，默认是 BallPulseFooter
//            ClassicsFooter(context).setDrawableSize(20f)
//        }
//    }
//}
