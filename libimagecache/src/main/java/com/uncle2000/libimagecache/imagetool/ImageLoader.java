//package com.uncle2000.libimagecache.imagetool;
//
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Environment;
//import android.widget.ImageView;
//
//import com.uncle2000.libutils.L;
//
//import java.io.File;
//import java.io.IOException;
//
//import static android.os.Environment.MEDIA_MOUNTED;
//
///**
// * Created by danger on 16/10/18.
// */
//
//public class ImageLoader {
//    private static final String hostPackageName = "com.mll.verification";
//
//    private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
//    private static final String INDIVIDUAL_DIR_NAME = "uil-images";
//
//    private static L logger = L.get();
//
//    private static boolean hasExternalStoragePermission(Context context) {
//        int perm = context.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION);
//        return perm == PackageManager.PERMISSION_GRANTED;
//    }
//
//    private static File getExternalCacheDir(Context context) {
//        File dataDir = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data");
//        File appCacheDir = new File(new File(dataDir, hostPackageName), "cache");
//        if (!appCacheDir.exists()) {
//            if (!appCacheDir.mkdirs()) {
//                logger.w("Unable to create external cache directory");
//                return null;
//            }
//            try {
//                new File(appCacheDir, ".nomedia").createNewFile();
//            } catch (IOException e) {
//                // L.i("Can't create \".nomedia\" file in application external cache directory");
//            }
//        }
//        return appCacheDir;
//    }
//
//    /**
//     * Returns application cache directory. Cache directory will be created on SD card
//     * <i>("/Android/data/[app_package_name]/cache")</i> (if card is mounted and app has appropriate permission) or
//     * on device's file system depending incoming parameters.
//     *
//     * @param context        Application context
//     * @param preferExternal Whether prefer external location for cache
//     * @return Cache {@link File directory}.<br />
//     * <b>NOTE:</b> Can be null in some unpredictable cases (if SD card is unmounted and
//     * {@link Context#getCacheDir() Context.getCacheDir()} returns null).
//     */
//    public static File getCacheDirectory(Context context, boolean preferExternal) {
//        File appCacheDir = null;
//        String externalStorageState;
//        try {
//            externalStorageState = Environment.getExternalStorageState();
//        } catch (NullPointerException | IncompatibleClassChangeError e) { // (sh)it happens (Issue #660)
//            externalStorageState = "";
//        }
//        if (preferExternal && MEDIA_MOUNTED.equals(externalStorageState) && hasExternalStoragePermission(context)) {
//            appCacheDir = getExternalCacheDir(context);
//        }
//        if (appCacheDir == null) {
//            appCacheDir = context.getCacheDir();
//        }
//        if (appCacheDir == null) {
//            String cacheDirPath = "/data/data/" + hostPackageName + "/cache/";
//            logger.w("Can't define system cache directory! '%s' will be used.", cacheDirPath);
//            appCacheDir = new File(cacheDirPath);
//        }
//        return appCacheDir;
//    }
//
////    public void loadImage(Context context, String url, final ImageView imageView) {
////        String fileName = MD5.md5(url);
////        File path = getCacheDirectory(context, true);
////
////        File cacheFile = new File(path, fileName);
////        if (cacheFile.exists()) {
////            displayImage(cacheFile, imageView);
////        } else {
////            FileDownloader.getInstance().downloadFile(url, cacheFile.getAbsolutePath(), new FileDownloader.FileDownloadListener() {
////                @Override
////                public void onProgress(String url, float percent, long nowSize, long totalSize) {
////
////                }
////
////                @Override
////                public void onSuccess(String url, File file) {
////                    // displayImage(file, imageView);
////                }
////
////                @Override
////                public void onFail(String url, int code, String message, Throwable errInfo) {
////
////                }
////            });
////        }
////    }
//
//    private void displayImage(File cacheFile, ImageView imageView) {
//        try {
//            if (imageView == null) {
//                return;
//            }
//            Bitmap bitmap = BitmapFactory.decodeFile(cacheFile.getAbsolutePath());
//            imageView.setImageBitmap(bitmap);
//        } catch (Exception | OutOfMemoryError e) {
//            e.printStackTrace();
//        }
//    }
//}
