//package com.uncle2000.libviews.dialog.android;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//
//public class NativeDialog {
//    private Context context;
//
//    public NativeDialog(Context context) {
//        this.context = context;
//    }
//
//    /* @setIcon 设置对话框图标
//     * @setTitle 设置对话框标题
//     * @setMessage 设置对话框消息提示
//     * setXXX方法返回Dialog对象，因此可以链式设置属性
//     */
//    public void showNormalDialog(String title, String content, String ok, String cancel,
//                                 DialogInterface.OnClickListener okListener,
//                                 DialogInterface.OnClickListener cancelListener) {
//        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
//        normalDialog.setTitle(title);
//        normalDialog.setMessage(content);
//        normalDialog.setPositiveButton(ok, okListener);
//        normalDialog.setNegativeButton(cancel, cancelListener);
//        // 显示
//        normalDialog.show();
//    }
//}
