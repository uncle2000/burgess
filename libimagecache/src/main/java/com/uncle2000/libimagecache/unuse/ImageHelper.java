//package com.uncle2000.libimagecache;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.support.annotation.DrawableRes;
//import android.support.annotation.Size;
//import android.widget.ImageView;
//
//import com.uncle2000.libutils.ToolUtil;
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
//import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
//import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//
///**
// * 功能：加载图片帮助类
// * Acceptable URIs examples
// * <p>
// * "http://site.com/image.png" // from Web
// * "file:///mnt/sdcard/image.png" // from SD card
// * "file:///mnt/sdcard/video.mp4" // from SD card (video thumbnail)
// * "content://media/external/images/media/13" // from content provider
// * "content://media/external/video/media/13" // from content provider (video thumbnail)
// * "assets://image.png" // from assets
// * "drawable://" + R.drawable.img // from drawables (non-9patch images)
// */
//public class ImageInterface {
//
//    private static String TAG = ImageInterface.class.getSimpleName();
//
//
//    public static DisplayImageOptions.Builder getDefaultOptions(int errorDrawable) {
//        return new DisplayImageOptions.Builder()
//                .cacheInMemory(true)
//                .cacheOnDisk(true)
//                .considerExifParams(true)
//                .showImageOnLoading(errorDrawable)
//                .showImageForEmptyUri(errorDrawable)
//                .showImageOnFail(errorDrawable);
//    }
//
//    private static String getUrlForUil(String url) {
//        if (url != null && url.startsWith("/")) {
//            url = "file://" + url;
//        }
//        return url;
//    }
//
//    public static void displayImage(String url, ImageView imageView, int errorDrawable) {
//        DisplayImageOptions options = getDefaultOptions(errorDrawable)
//                .build();
//
//        url = getUrlForUil(url);
//        displayImage(url, imageView, options, null);
//    }
//
//    public static void displayImage(String url, ImageView imageView, int errorDrawable, ImageLoadingListener listener) {
//        DisplayImageOptions options = getDefaultOptions(errorDrawable)
//                .build();
//
//        url = getUrlForUil(url);
//        displayImage(url, imageView, options, listener);
//    }
//
//    public static void displayImage(String url, ImageView imageView, DisplayImageOptions options, ImageLoadingListener listener) {
//
//        url = getUrlForUil(url);
//        ImageLoader.getInstance().displayImage(url, imageView, options, listener);
//    }
//
//    /**
//     * 展示圆角图片
//     *
//     * @param url
//     * @param imageView
//     * @param errorDrawable
//     */
//    public static void displayRoundImage(String url, ImageView imageView, int errorDrawable) {
//        displayRoundImage(url, imageView, errorDrawable, null);
//    }
//
//    /**
//     * 展示圆角图片
//     *
//     * @param url
//     * @param imageView
//     * @param errorDrawable
//     */
//    public static void displayRoundImage(String url, ImageView imageView, int errorDrawable, ImageLoadingListener listener) {
//        DisplayImageOptions options = getDefaultOptions(errorDrawable)
//                .displayer(new RoundedBitmapDisplayer(14))
//                .build();
//
//        url = getUrlForUil(url);
//
//        ImageLoader.getInstance().displayImage(url, imageView, options, listener);
//    }
//
//    /**
//     * 展示圆角图片，角度为4，比displayRoundImage小
//     *
//     * @param url
//     * @param imageView
//     * @param errorDrawable
//     */
//    public static void displayFilletImage(String url, ImageView imageView, int errorDrawable) {
//        int cornerSize = ToolUtil.dip2px(imageView.getContext(), 3);
//        DisplayImageOptions options = getDefaultOptions(errorDrawable)
//                .displayer(new RoundedBitmapDisplayer(cornerSize))
//                .build();
//
//        url = getUrlForUil(url);
//
//        ImageLoader.getInstance().displayImage(url, imageView, options);
//    }
//
//    public static void displayRoundImageFix(String url, ImageView imageView, int errorDrawable, ImageLoadingListener listener) {
//        DisplayImageOptions options = getDefaultOptions(errorDrawable)
//                .displayer(new RoundedBitmapDisplayerFix(imageView, 14))
//                .build();
//
//        url = getUrlForUil(url);
//
//        ImageLoader.getInstance().displayImage(url, imageView, options, listener);
//    }
//
//    /**
//     * 展示可定制圆角的图片
//     *
//     * @param url
//     * @param imageView
//     * @param errorDrawable
//     */
//
//    public static void customizableDisplayRoundImage(String url, ImageView imageView, int errorDrawable,
//                                                     boolean isLeftTopcornerRound, boolean isRightTopcornerRound,
//                                                     boolean isLeftBottomcornerRound, boolean isRightBottomcornerRound,
//                                                     ImageLoadingListener listener) {
//        customizableDisplayRoundImage(url, imageView, 5, errorDrawable, isLeftTopcornerRound, isRightTopcornerRound,
//                isLeftBottomcornerRound, isRightBottomcornerRound, listener);
//    }
//
//    public static void customizableDisplayRoundImage(String url, ImageView imageView, int radio, int errorDrawable,
//                                                     boolean isLeftTopcornerRound, boolean isRightTopcornerRound,
//                                                     boolean isLeftBottomcornerRound, boolean isRightBottomcornerRound,
//                                                     ImageLoadingListener listener) {
//        DisplayImageOptions options = getDefaultOptions(errorDrawable)
//                .displayer(new CustomizableRoundedBitmapDisplayer(imageView, ToolUtil.dip2px(App.getInstance(), radio),
//                        isLeftTopcornerRound, isRightTopcornerRound,
//                        isLeftBottomcornerRound, isRightBottomcornerRound))
//                .build();
//
//        url = getUrlForUil(url);
//
//        ImageLoader.getInstance().displayImage(url, imageView, options, listener);
//    }
//
//    /**
//     * 展示圆形图片
//     *
//     * @param url
//     * @param imageView
//     * @param errorDrawable
//     */
//    public static void displayCircleImage(String url, ImageView imageView, int errorDrawable) {
//        DisplayImageOptions options = getDefaultOptions(errorDrawable)
//                .displayer(new CircleBitmapDisplayer())
//                .build();
//
//        url = getUrlForUil(url);
//        ImageLoader.getInstance().displayImage(url, imageView, options);
//    }
//
//    /**
//     * 展示圆形灰色图片
//     *
//     * @param url
//     * @param imageView
//     * @param errorDrawable
//     */
//    public static void displayGrayCircleImage(String url, ImageView imageView, int errorDrawable) {
//        DisplayImageOptions options = getDefaultOptions(errorDrawable)
//                .displayer(new GrayCircleBitmapDisplayer())
//                .build();
//
//        url = getUrlForUil(url);
//        ImageLoader.getInstance().displayImage(url, imageView, options);
//    }
//
//
//    /**
//     * 展示直角高斯模糊图
//     */
//    public static void displayBlurImage(String url, ImageView imageView,
//                                        @DrawableRes int errorDrawable, @Size(min = 1) int blur) {
//        DisplayImageOptions options = getDefaultOptions(errorDrawable)
//                .postProcessor(b -> BitmapUtil.createBlurBitmap(b, blur))
//                .build();
//
//        url = getUrlForUil(url);
//        ImageLoader.getInstance().displayImage(url, imageView, options);
//    }
//
//    /**
//     * 保存bitmap
//     */
//    public static boolean saveBitmap(Bitmap bmp, String filename) {
//        Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
//        int quality = 100;
//        boolean result = false;
//        OutputStream stream = null;
//        try {
//            stream = new FileOutputStream(filename);
//            result = bmp.compress(format, quality, stream);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (stream != null) {
//                    stream.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//    public static void cancelDisplayTask(ImageView imageView) {
//        ImageLoader.getInstance().cancelDisplayTask(imageView);
//    }
//
//    /**
//     * 从一个url下载图片到指定 本地目录
//     *
//     * @param url
//     * @return 会返回url 以便需要sp存储的 存放
//     */
//    public static String downLoadImageFromUrl(String url, String path, Context context) {
//        InputStream inputStream = null;
//        FileOutputStream fos = null;
//        try {
//            URLConnection connection = new URL(url).openConnection();
//            connection.setConnectTimeout(10000);
//            connection.setDoInput(true);
//            connection.connect();
//            inputStream = connection.getInputStream();
//            fos = new FileOutputStream(path);
//            int len = 0;
//            byte[] buf = new byte[1024];
//            while ((len = inputStream.read(buf)) != -1) {
//                fos.write(buf, 0, len);
//            }
//            fos.flush();
//            return url;
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//            return null;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            try {
//                if (fos != null)
//                    fos.close();
//                if (inputStream != null)
//                    inputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void clearCache(String uri, ImageView imageView) {
//
//        // getDefaultOptions(0).con
//        // if (targetSize == null) {
//        //     targetSize = ImageSizeUtils.defineTargetSizeForView(
//        //             new ImageViewAware(imageView), new ImageLoaderConfiguration().getMaxImageSize());
//        // }
//        // String memoryCacheKey = MemoryCacheUtils.generateKey(uri, targetSize);
//        ImageLoader.getInstance().getMemoryCache().clear();
//    }
//
//}
