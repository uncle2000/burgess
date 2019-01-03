//package com.uncle2000.libutils
//
//import android.Manifest
//import android.annotation.SuppressLint
//import android.annotation.TargetApi
//import android.app.Activity
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.os.Build
//import android.support.v4.app.ActivityCompat
//import android.support.v4.content.ContextCompat
//
///**
// * Created by Administrator on 2017/12/15/015.
// */
//
//object PhoneTool {
//
//    /**
//     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
//     *
//     * @param phoneNum 电话号码
//     */
//    fun callPhone(context: Activity, phoneNum: String) {
//        val intent = Intent(Intent.ACTION_DIAL)
//        val data = Uri.parse("tel:$phoneNum")
//        intent.data = data
//        context.startActivity(intent)
//    }
//
//    @SuppressLint("MissingPermission")
//    fun makeCall(context: Activity, phoneNumber: String): Boolean {
//        if (Build.VERSION.SDK_INT >= 23) {
//            return makeCall23(context, phoneNumber)
//        } else {
//
//            // CallDao callDao = DB . get ().getSession().getCallDao();
//            // callDao.insert(callModel);
//            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber))
//            try {
//                context.startActivity(intent)
//                return true
//            } catch (ignore: SecurityException) {
//            }
//        }
//        return false
//    }
//
//    @SuppressLint("MissingPermission")
//    @TargetApi(23)
//    private fun makeCall23(context: Activity, phoneNumber: String): Boolean {
//        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
//                == PackageManager.PERMISSION_GRANTED) {
//
//            // val callDao = DB.get().session.callDao
//            // callDao.insert(callModel);
//            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber))
//            context.startActivity(intent)
//            return true
//        } else {
//            ActivityCompat.requestPermissions(context,
//                    arrayOf(Manifest.permission.CALL_PHONE),
//                    1)
//        }
//
//        return false
//    }
//
////    /**
////     * Call from Activity#onRequestPermissionsResult
////     *
////     * @param requestCode
////     * @param permissions
////     * @param grantResults
////     */
////    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
////    {
////        if (requestCode == cameraPermissionReqCode) {
////            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////
////                CallDao callDao = DB . get ().getSession().getCallDao();
////                callDao.insert(callModel);
////                // CallTable.getInstance().insertCallHistory(callModel);
////                // DBManager.getInstance(getActivity()).insertCallRecord(VApplication.getUserModel().getSysuuid(),callModel);
////                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + callModel.getTel()));
////                startActivity(intent);
////                dismiss();
////            } else {
////                T.show("您拒绝了该权限，请到应用设置中手动开启打电话的权限", 1000);
////            }
////        }
////    }
//}
