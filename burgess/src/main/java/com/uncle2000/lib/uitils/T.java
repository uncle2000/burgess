package com.uncle2000.lib.uitils;

import android.os.Looper;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Toast统一管理类
 */
public class T {

    public static boolean isShow = true;

    private T() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(CharSequence message) {
//        try {
//            if (isShow && App.INSTANCE != null) {
//
//                Toast toast = Toast.makeText(App.INSTANCE, message, Toast.LENGTH_SHORT);
//                //处理换行文字居中问题
//                try {
//                    ViewGroup view = (ViewGroup) toast.getView();
//                    if (view.getChildCount() > 0) {
//                        TextView textView = (TextView) view.getChildAt(0);
//                        textView.setGravity(Gravity.CENTER);
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                toast.show();
//            }
//        } catch (Exception e) {
//            //解决在子线程中调用Toast的异常情况处理
//            Looper.prepare();
//            Toast.makeText(App.INSTANCE, message, Toast.LENGTH_SHORT).show();
//            Looper.loop();
//        }
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(int message) {
//        if (isShow)
//            Toast.makeText(App.INSTANCE, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
//        if (isShow)
//            Toast.makeText(App.INSTANCE, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(int message) {
//        if (isShow)
//            Toast.makeText(App.INSTANCE, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(CharSequence message, int duration) {
//        if (isShow)
//            Toast.makeText(App.INSTANCE, message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(int message, int duration) {
//        if (isShow)
//            Toast.makeText(App.INSTANCE, message, duration).show();
    }

}