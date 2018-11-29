//package com.uncle2000.libutils;
//
//import android.app.Activity;
//import android.content.pm.PackageManager;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//
///**
// * Created by danger on 16/7/11.
// */
//
//public class PermissionUtil {
//
//    public static final int PERMISSION_READ_PHOTO = 1;
//    public static final int PERMISSION_TAKE_PHOTO = 2;
//    public static final int PERMISSION_RECORD_AUDIO = 3;
//    public static final int PERMISSION_WRITE_PHOTO = 4;
//    public static final int PERMISSION_WR_SDCARD = 5;
//
//    public static boolean requestPermissions(Activity activity, String permission, int code) {
//        if (App.getInstance() == null) {
//            return true;
//        }
//        int checkResult = ContextCompat.checkSelfPermission(activity, permission);
//
//        if (checkResult == PackageManager.PERMISSION_GRANTED) {
//            return true;
//        }
//
//
//        //申请WRITE_EXTERNAL_STORAGE权限
//        ActivityCompat.requestPermissions(
//                activity,
//                new String[]{permission},
//                code);
//        return false;
//    }
//
//    public static boolean requestPermissions(Activity activity, int code, String... permissions) {
//        if (App.getInstance() == null) {
//            return true;
//        }
//
//        boolean needReq = false;
//        for (String permission : permissions) {
//            int checkResult = ContextCompat.checkSelfPermission(activity, permission);
//            if (checkResult != PackageManager.PERMISSION_GRANTED) {
//                needReq = true;
//                break;
//            }
//        }
//
//        if (!needReq) {
//            return true;
//        }
//
//        //申请WRITE_EXTERNAL_STORAGE权限
//        ActivityCompat.requestPermissions(
//                activity,
//                permissions,
//                code);
//        return false;
//    }
//}
