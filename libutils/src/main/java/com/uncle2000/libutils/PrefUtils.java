//package com.uncle2000.libutils;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//
///**
// * Created by danger
// * on 16/7/24.
// */
//public class PrefUtils {
//
//    // static Context context;
//    static SharedPreferences sp;
//
//    public static void init(Context context, String prefName) {
//        // PrefUtils.context = context.getApplicationContext();
//        sp = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
//    }
//
//    public static boolean getBoolean(String key, boolean defalt) {
//        // sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
//        return sp.getBoolean(key, defalt);
//    }
//
//    public static void putAny(String key, Boolean value) {
//        // SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
//        sp.edit().putAny(key, value).commit();
//    }
//
//    public static String getAny(String key, String defalt) {
//        // SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
//        return sp.getAny(key, defalt);
//    }
//
//    public static void putAny(String key, String value) {
//        // SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
//        sp.edit().putAny(key, value).commit();
//
//    }
//
//    public static int getAny(String key, int defalt) {
//        // SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
//        return sp.getAny(key, defalt);
//    }
//
//    public static void putAny(String key, int value) {
//        // SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
//        sp.edit().putAny(key, value).commit();
//
//    }
//
//    public static long getAny(String key, long defalt) {
//        // SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
//        return sp.getAny(key, defalt);
//    }
//
//    public static void putLong(String key, long value) {
//        // SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
//        sp.edit().putLong(key, value).commit();
//    }
//
////    public static void putJson(String key, Object obj) {
////        String content = "";
////        if (obj != null) {
////            content = JSON.toJSONString(obj);
////        }
////        // SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
////        sp.edit().putAny(key, content).commit();
////    }
////
////    public static <T> T getJson(String key, Class<T> cls) {
////        // SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
////
////        String jsonString = getAny(key, null);
////        Object obj = null;
////
////        if (jsonString != null) {
////            obj = JSON.parseObject(jsonString, cls);
////        }
////        return (T) obj;
////    }
//}
