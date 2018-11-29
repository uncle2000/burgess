//package com.uncle2000.libimagecache;
//
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.Bitmap.Config;
//import android.graphics.BitmapFactory;
//import android.graphics.Canvas;
//import android.graphics.Matrix;
//import android.graphics.Paint;
//import android.graphics.PorterDuff.Mode;
//import android.graphics.PorterDuffXfermode;
//import android.graphics.Rect;
//import android.graphics.RectF;
//import android.media.ExifInterface;
//import android.net.Uri;
//import android.provider.MediaStore;
//import android.util.Base64;
//import android.widget.ImageView;
//
//
//import com.uncle2000.libutils.ToolUtil;
//
//import java.io.BufferedOutputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//public class BitmapUtil {
//    public static final int DEFOULT_SIZE = 1024 * 100;
//    public static final int MAX_ICON_LENGTH = 120;
//    private static final float HEIGHT = 432;
//    private static final float WIDTH = 720;
//
//    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
//
//        if (degree == 0)
//            return bm;
//
//        Bitmap returnBm = null;
//        // 根据旋转角度，生成旋转矩阵
//        Matrix matrix = new Matrix();
//        matrix.postRotate(degree);
//        try {
//            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
//            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
//        } catch (OutOfMemoryError e) {
//            e.printStackTrace();
//        }
//        if (returnBm == null) {
//            returnBm = bm;
//        }
//        if (bm != returnBm) {
//            bm.recycle();
//        }
//        return returnBm;
//    }
//
//    public static int getBitmapDegree(String path) {
//        int degree = 0;
//        try {
//            // 从指定路径下读取图片，并获取其EXIF信息
//            ExifInterface exifInterface = new ExifInterface(path);
//            // 获取图片的旋转信息
//            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
//                    ExifInterface.ORIENTATION_NORMAL);
//            switch (orientation) {
//                case ExifInterface.ORIENTATION_ROTATE_90:
//                    degree = 90;
//                    break;
//                case ExifInterface.ORIENTATION_ROTATE_180:
//                    degree = 180;
//                    break;
//                case ExifInterface.ORIENTATION_ROTATE_270:
//                    degree = 270;
//                    break;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return degree;
//    }
//
//    public static Bitmap getLocalFileImageBitmap(String path, int maxNumOfPixels) {
//        BitmapFactory.Options opts = new BitmapFactory.Options();
//        opts.inJustDecodeBounds = true;
//        try {
//            BitmapFactory.decodeFile(path, opts);
//        } catch (Exception e) {
//            return null;
//        }
//        opts.inJustDecodeBounds = false;
//        if (maxNumOfPixels <= 0) {
//            opts.inSampleSize = computeSampleSize(opts, -1, DEFOULT_SIZE);
//        } else {
//            opts.inSampleSize = computeSampleSize(opts, -1, maxNumOfPixels);
//        }
//        return BitmapFactory.decodeFile(path, opts);
//    }
//
//
//    public static Bitmap loadImageFromLocal(final String path, final ImageView imageView) {
//        //		getBitmapDegree(path);
//        Bitmap bm;
//        // 加载图片
//        // 图片的压缩
//        // 1、获得图片需要显示的大小
//        ImageSizeUtil.ImageSize imageSize = ImageSizeUtil.getImageViewSize(imageView);
//        // 2、压缩图片
//        bm = decodeSampledBitmapFromPath(path, imageSize.width,
//                imageSize.height);
//        return bm;
//    }
//
//    public static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
//        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
//
//        int roundedSize;
//        if (initialSize <= 8) {
//            roundedSize = 1;
//            while (roundedSize < initialSize) {
//                roundedSize <<= 1;
//            }
//        } else {
//            roundedSize = (initialSize + 7) / 8 * 8;
//        }
//
//        return roundedSize;
//    }
//
//    public static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
//        double w = options.outWidth;
//        double h = options.outHeight;
//
//        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
//        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));
//
//        if (upperBound < lowerBound) {
//            return lowerBound;
//        }
//        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
//            return 1;
//        } else if (minSideLength == -1) {
//            return lowerBound;
//        } else {
//            return upperBound;
//        }
//    }
//
//    // public static Bitmap iconMethod(Bitmap orgBimtmap, int ringWidth, int
//    // sex){
//    // int width = orgBimtmap.getWidth();
//    // int height = orgBimtmap.getHeight();
//    // Bitmap bitmap = Bitmap.createBitmap(width + 2 * ringWidth, height + 2 *
//    // ringWidth, Config.ARGB_8888);
//    // Canvas canvas = new Canvas(bitmap);
//    //
//    // //改为圆形
//    // Paint paint = new Paint();
//    // Rect rect = new Rect(0, 0, width, height);
//    // RectF rectF = new RectF(ringWidth, ringWidth, width + ringWidth, height +
//    // ringWidth);
//    // paint.setAntiAlias(true);
//    // canvas.drawARGB(0, 0, 0, 0);
//    // paint.setColor(0xff424242);
//    // canvas.drawRoundRect(rectF, 90f, 90f, paint);
//    // paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
//    // canvas.drawBitmap(orgBimtmap, rect, rectF, paint);
//    // //绘制圆环
//    // int center = (width + 2 * ringWidth) / 2;
//    // paint = new Paint();
//    // paint.setStyle(Paint.Style.STROKE); //绘制空心圆
//    // if (sex == 0) {
//    // paint.setColor(0xff1aa8d6);
//    // }
//    // else if (sex == 1) {
//    // paint.setColor(0xfff342bb);
//    // }
//    // paint.setAntiAlias(true);
//    // paint.setStrokeWidth(ringWidth);
//    // canvas.drawCircle(center, center, (width + ringWidth) / 2, paint);
//    // return bitmap;
//    // }
//
//    /**
//     * 将bitmap保存为jpg文件
//     *
//     * @param bitmap
//     * @param file
//     * @param quality 图片质量80 ~ 100， 默认为100
//     * @return
//     */
//    public static boolean saveBitmapToJpgFile(Bitmap bitmap, File file, int quality) {
//        if (bitmap != null && !bitmap.isRecycled() && file != null) {
//            if (file.exists()) {
//                file.delete();
//            }
//            int q;
//            if (quality < 80 || quality > 100) {
//                q = 100;
//            } else {
//                q = quality;
//            }
//            try {
//                FileOutputStream outputStream = new FileOutputStream(file);
//                bitmap.compress(Bitmap.CompressFormat.JPEG, q, outputStream);
//                outputStream.flush();
//                outputStream.close();
//                file.setLastModified(System.currentTimeMillis());
//                return true;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return false;
//    }
//
//    /**
//     * 保存图片到系统相册
//     *
//     * @param context
//     * @param bmp
//     * @param dir
//     * @param fileName
//     */
//    public static boolean saveImageToGallery(Context context, Bitmap bmp, String dir, String fileName) {
//        File file;
//        String title;
//        if (bmp != null) {
//            // 首先保存图片
//            title = fileName;
//            File appDir = new File(dir);
//            if (!appDir.exists()) {
//                appDir.mkdirs();
//            }
//            file = new File(appDir, fileName);
//            try {
//                FileOutputStream fos = new FileOutputStream(file);
//                bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//                fos.flush();
//                fos.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//                return false;
//            } catch (IOException e) {
//                e.printStackTrace();
//                return false;
//            }
//        } else {
//            file = new File(dir, fileName);
//            title = file.getName();
//        }
//
//
//        // 其次把文件插入到系统图库
//        //        String url = MediaStore.Images.Media.insertImage(context.getContentResolver(),
//        //                    bmp, fileName, null);
//
//
//        ContentValues values = new ContentValues();
//        values.put(MediaStore.Images.Media.TITLE, title);
//        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
//        values.put(MediaStore.Images.Media.BUCKET_ID, "精灵S".hashCode());
//        values.put(MediaStore.Images.Media.BUCKET_DISPLAY_NAME, "精灵S");
//        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
//        //        values.put( MediaStore.Images.Media.DESCRIPTION, context.getResources().getAny( R.string.product_image_description ) );
//        values.put(MediaStore.MediaColumns.DATA, file.getAbsolutePath());
//        Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//
//        //        return uri;
//        // 最后通知图库更新
//        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
//        return true;
//    }
//
//    /**
//     * 头像处理
//     *
//     * @param orgBimtmap
//     * @return
//     */
//    public static Bitmap getUserIconBitmap(Bitmap orgBimtmap) {
//        if (orgBimtmap != null) {
//            Bitmap org = null;
//            Bitmap dest = null;
//            int width = orgBimtmap.getWidth();
//            int height = orgBimtmap.getHeight();
//            int length = width <= height ? width : height;
//            if (width != height) {
//                org = Bitmap.createBitmap(orgBimtmap, (width - length) / 2, (height - length) / 2, length, length);
//                orgBimtmap.recycle();
//            } else {
//                org = orgBimtmap;
//            }
//            if (length != MAX_ICON_LENGTH) {
//                float scaledLen = (MAX_ICON_LENGTH / (float) length) * length;
//                length = (int) scaledLen;
//                dest = Bitmap.createScaledBitmap(org, length, length, true);
//                org.recycle();
//            } else {
//                dest = org;
//            }
//            Bitmap bitmap = Bitmap.createBitmap(MAX_ICON_LENGTH, MAX_ICON_LENGTH, Config.ARGB_8888);
//            Canvas canvas = new Canvas(bitmap);
//
//            // 改为圆形
//            Paint paint = new Paint();
//            Rect rect = new Rect(0, 0, MAX_ICON_LENGTH, MAX_ICON_LENGTH);
//            RectF rectF = new RectF(0, 0, MAX_ICON_LENGTH, MAX_ICON_LENGTH);
//            paint.setAntiAlias(true);
//            canvas.drawARGB(0, 0, 0, 0);
//            paint.setColor(0xff424242);
//            canvas.drawRoundRect(rectF, 90f, 90f, paint);
//            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
//            canvas.drawBitmap(dest, rect, rectF, paint);
//            dest.recycle();
//            return bitmap;
//        }
//        return null;
//    }
//
//    public static Bitmap bgImageMethod(Bitmap orgBitmap) {
//        float width = orgBitmap.getWidth();
//        float height = orgBitmap.getHeight();
//        if (WIDTH != width && HEIGHT != height) {
//            float scale = WIDTH / width >= HEIGHT / height ? WIDTH / width : HEIGHT / height;
//            int wid = (int) (width * scale);
//            int hei = (int) (height * scale);
//            if (wid < WIDTH) {
//                wid = (int) WIDTH;
//            }
//            if (hei < HEIGHT) {
//                hei = (int) HEIGHT;
//            }
//            Bitmap bmpScaled = Bitmap.createScaledBitmap(orgBitmap, wid, hei, true);
//            Bitmap bmp = Bitmap.createBitmap(bmpScaled, (int) (bmpScaled.getWidth() - WIDTH) / 2, (int) (bmpScaled.getHeight() - HEIGHT) / 2, (int) WIDTH, (int) HEIGHT);
//            bmpScaled.recycle();
//            return bmp;
//        } else {
//            return orgBitmap.copy(Config.ARGB_8888, true);
//        }
//    }
//
//
//    protected static Bitmap decodeSampledBitmapFromPath(String path, int width, int height) {
//        File file = new File(path);
//        if (!file.exists()) {
//            //			file.delete();
//            file = null;
//            return null;
//        }
//        // 获得图片的宽和高，并不把图片加载到内存中
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(path, options);
//
//        options.inSampleSize = ImageSizeUtil.caculateInSampleSize(options,
//                width, height);
//
//        // 使用获得到的InSampleSize再次解析图片
//        options.inJustDecodeBounds = false;
//        try {
//            return BitmapFactory.decodeFile(path, options);
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//
//    public static Bitmap getBitmapFromUri(Context context, Uri uri) {
//        Bitmap bitmap = null;
//        if (uri != null) {
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return bitmap;
//    }
//
//    public static Bitmap zoomBitmap(Bitmap bm, int w, int h) {
//
//        Bitmap newBitmap;
//        if (bm == null) {
//            return null;
//        }
//        float scaleWidth = bm.getWidth() * 1.0f / w;
//        float scaleHeight = bm.getHeight() * 1.0f / h;
//        float scale = scaleWidth > scaleHeight ? scaleWidth : scaleHeight;
//        if (scale > 1.01) {
//            newBitmap = Bitmap.createScaledBitmap(bm, (int) (bm.getWidth() / scale), (int) (bm.getHeight() / scale), false);
//        } else {
//            newBitmap = bm;
//        }
//        return newBitmap;
//    }
//
//    public static String bitmapToBase64(Bitmap bitmap) {
//
//        String result = null;
//        ByteArrayOutputStream baos = null;
//        try {
//            if (bitmap != null) {
//                baos = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//
//                baos.flush();
//                baos.close();
//
//                byte[] bitmapBytes = baos.toByteArray();
//                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (baos != null) {
//                    baos.flush();
//                    baos.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }
//
//    /**
//     * base64转为bitmap
//     *
//     * @param base64Data
//     * @return
//     */
//    public static Bitmap base64ToBitmap(String base64Data) {
//        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
//        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//    }
//
//    public static ImageSizeUtil.ImageSize getFileSize(String imageFileName) {
//        try {
//            // 获得图片的宽和高，并不把图片加载到内存中
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inJustDecodeBounds = true;
//            BitmapFactory.decodeFile(imageFileName, options);
//            return new ImageSizeUtil.ImageSize(options.outWidth, options.outHeight);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
////    public static File compressImage(String file, int maxLength) {
////
////        BitmapFactory.Options opts = new BitmapFactory.Options();
////        opts.inJustDecodeBounds = true;
////        try {
////            BitmapFactory.decodeFile(file, opts);
////        } catch (Exception e) {
////            return null;
////        }
////        opts.inJustDecodeBounds = false;
////
////        int degree = BitmapUtil.getBitmapDegree(file);
////        float scalex = 1.0f * maxLength / opts.outWidth; // 1280 / 2560 = 0.5
////        float scaley = 1.0f * maxLength / opts.outHeight; // 1280 / 4000 = 0.8
////
////        File fout = ToolUtil.getOutputPictureFile(App.getInstance());
////        if (fout == null) {
////            return null;
////        }
////        if (fout.exists()) {
////            if (!fout.delete()) {
////                return null;
////            }
////        }
////        int ret = ImageTool.compressImage(
////                App.getInstance(), file, fout.getAbsolutePath(), 34,
////                Math.min(scalex, scaley), 360 - degree);
////        if (ret == 0 && fout.exists()) {
////            return fout;
////        }
////        fout.delete();
////        return null;
////    }
//
//    public static File compressImageUseJava(String path) {
//        Bitmap bitmap1, bitmap2;
//        File compressToFile = null;
//        try {
//
//            bitmap1 = BitmapUtil.getLocalFileImageBitmap(path, 720 * 1280); // BitmapFactory.decodeFile(path);
//            int degree = BitmapUtil.getBitmapDegree(path);
//            bitmap2 = BitmapUtil.rotateBitmapByDegree(bitmap1, degree);
//            //wendjiatag感觉bitmap1到这里就已经被用完了  就已经可以回收了.
//
//            File compressToDir = ToolUtil.getOutputImagePath(App.getInstance());
//            compressToFile = new File(compressToDir, new File(path).getName() + ".compresse.jpg");
//
//            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(compressToFile));
//            bitmap2.compress(Bitmap.CompressFormat.JPEG, 40, bos);
//            bos.flush();
//            bos.close();
//
//            if (compressToFile.exists()) {
//                return compressToFile;
//            }
//        } catch (OutOfMemoryError e) {
//            e.printStackTrace();
//            // ImageLoader.getInstance().clearMemoryCache();
//            e.printStackTrace();
//            // T.showShort(R.string.out_of_memory_error);
//            return null;
//        } catch (IOException e) {
//            e.printStackTrace();
//            // ImageLoader.getInstance().clearMemoryCache();
//            e.printStackTrace();
//            // T.showShort(R.string.out_of_memory_error);
//            return null;
//        } catch (Exception e) {
//            e.printStackTrace();
//            // ImageLoader.getInstance().clearMemoryCache();
//            e.printStackTrace();
//            // T.showShort(R.string.out_of_memory_error);
//            return null;
//        }
//
//        bitmap1.recycle();
//        bitmap2.recycle();
//
//        return null;
//    }
//
//
//    /**
//     * 8      * 创建一个虚化的位图
//     * 9      * @param sentBitmap 原位图
//     * 10      * @param radius 虚化半径
//     * 11      * @return 虚化后的位图
//     * 12
//     */
//    public static Bitmap createBlurBitmap(Bitmap sentBitmap, int radius) {
//        if (null == sentBitmap)
//            return null;
//        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
//        if (radius < 1) {
//            return (null);
//        }
//        int w = bitmap.getWidth();
//        int h = bitmap.getHeight();
//        int[] pix = new int[w * h];
//        bitmap.getPixels(pix, 0, w, 0, 0, w, h);
//        int wm = w - 1;
//        int hm = h - 1;
//        int wh = w * h;
//        int div = radius + radius + 1;
//        int r[] = new int[wh];
//        int g[] = new int[wh];
//        int b[] = new int[wh];
//        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
//        int vmin[] = new int[Math.max(w, h)];
//        int divsum = (div + 1) >> 1;
//        divsum *= divsum;
//        int dv[] = new int[256 * divsum];
//        for (i = 0; i < 256 * divsum; i++) {
//            dv[i] = (i / divsum);
//        }
//        yw = yi = 0;
//        int[][] stack = new int[div][3];
//        int stackpointer;
//        int stackstart;
//        int[] sir;
//        int rbs;
//        int r1 = radius + 1;
//        int routsum, goutsum, boutsum;
//        int rinsum, ginsum, binsum;
//        for (y = 0; y < h; y++) {
//            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
//            for (i = -radius; i <= radius; i++) {
//                p = pix[yi + Math.min(wm, Math.max(i, 0))];
//                sir = stack[i + radius];
//                sir[0] = (p & 0xff0000) >> 16;
//                sir[1] = (p & 0x00ff00) >> 8;
//                sir[2] = (p & 0x0000ff);
//                rbs = r1 - Math.abs(i);
//                rsum += sir[0] * rbs;
//                gsum += sir[1] * rbs;
//                bsum += sir[2] * rbs;
//                if (i > 0) {
//                    rinsum += sir[0];
//                    ginsum += sir[1];
//                    binsum += sir[2];
//                } else {
//                    routsum += sir[0];
//                    goutsum += sir[1];
//                    boutsum += sir[2];
//                }
//            }
//            stackpointer = radius;
//            for (x = 0; x < w; x++) {
//                r[yi] = dv[rsum];
//                g[yi] = dv[gsum];
//                b[yi] = dv[bsum];
//                rsum -= routsum;
//                gsum -= goutsum;
//                bsum -= boutsum;
//                stackstart = stackpointer - radius + div;
//                sir = stack[stackstart % div];
//                routsum -= sir[0];
//                goutsum -= sir[1];
//                boutsum -= sir[2];
//                if (y == 0) {
//                    vmin[x] = Math.min(x + radius + 1, wm);
//                }
//                p = pix[yw + vmin[x]];
//                sir[0] = (p & 0xff0000) >> 16;
//                sir[1] = (p & 0x00ff00) >> 8;
//                sir[2] = (p & 0x0000ff);
//                rinsum += sir[0];
//                ginsum += sir[1];
//                binsum += sir[2];
//                rsum += rinsum;
//                gsum += ginsum;
//                bsum += binsum;
//                stackpointer = (stackpointer + 1) % div;
//                sir = stack[(stackpointer) % div];
//                routsum += sir[0];
//                goutsum += sir[1];
//                boutsum += sir[2];
//                rinsum -= sir[0];
//                ginsum -= sir[1];
//                binsum -= sir[2];
//                yi++;
//            }
//            yw += w;
//        }
//        for (x = 0; x < w; x++) {
//            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
//            yp = -radius * w;
//            for (i = -radius; i <= radius; i++) {
//                yi = Math.max(0, yp) + x;
//                sir = stack[i + radius];
//                sir[0] = r[yi];
//                sir[1] = g[yi];
//                sir[2] = b[yi];
//                rbs = r1 - Math.abs(i);
//                rsum += r[yi] * rbs;
//                gsum += g[yi] * rbs;
//                bsum += b[yi] * rbs;
//                if (i > 0) {
//                    rinsum += sir[0];
//                    ginsum += sir[1];
//                    binsum += sir[2];
//                } else {
//                    routsum += sir[0];
//                    goutsum += sir[1];
//                    boutsum += sir[2];
//                }
//                if (i < hm) {
//                    yp += w;
//                }
//            }
//            yi = x;
//            stackpointer = radius;
//            for (y = 0; y < h; y++) {
//                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16) | (dv[gsum] << 8) | dv[bsum];
//                rsum -= routsum;
//                gsum -= goutsum;
//                bsum -= boutsum;
//                stackstart = stackpointer - radius + div;
//                sir = stack[stackstart % div];
//                routsum -= sir[0];
//                goutsum -= sir[1];
//                boutsum -= sir[2];
//                if (x == 0) {
//                    vmin[y] = Math.min(y + r1, hm) * w;
//                }
//                p = x + vmin[y];
//                sir[0] = r[p];
//                sir[1] = g[p];
//                sir[2] = b[p];
//                rinsum += sir[0];
//                ginsum += sir[1];
//                binsum += sir[2];
//                rsum += rinsum;
//                gsum += ginsum;
//                bsum += binsum;
//                stackpointer = (stackpointer + 1) % div;
//                sir = stack[stackpointer];
//                routsum += sir[0];
//                goutsum += sir[1];
//                boutsum += sir[2];
//                rinsum -= sir[0];
//                ginsum -= sir[1];
//                binsum -= sir[2];
//                yi += w;
//            }
//        }
//        bitmap.setPixels(pix, 0, w, 0, 0, w, h);
//        return (bitmap);
//    }
//}
