//package com.uncle2000.libimagecache;
//
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.ColorMatrix;
//import android.graphics.ColorMatrixColorFilter;
//
//import com.nostra13.universalimageloader.core.assist.LoadedFrom;
//import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
//import com.nostra13.universalimageloader.core.imageaware.ImageAware;
//import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
//
///**
// * Created by xiao on 2016/7/13.
// * 功能: 展示圆形灰色图片
// * 需求地址:
// */
//public class GrayCircleBitmapDisplayer extends CircleBitmapDisplayer {
//
//    @Override
//    public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
//        if (!(imageAware instanceof ImageViewAware)) {
//            throw new IllegalArgumentException("ImageAware should wrap ImageView. ImageViewAware is expected.");
//        }
//
//        imageAware.setImageDrawable(new UserIconDrawable(bitmap, strokeColor, strokeWidth));
//    }
//
//    public static class UserIconDrawable extends CircleDrawable {
//
//        public UserIconDrawable(Bitmap bitmap, Integer strokeColor, float strokeWidth) {
//            super(bitmap, strokeColor, strokeWidth);
//        }
//
//        @Override
//        public void draw(Canvas canvas) {
//            super.draw(canvas);
////            Paint paint = new Paint();
//            ColorMatrix cm = new ColorMatrix();
//            cm.setSaturation(0);
//            ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
//            paint.setColorFilter(f);
//            canvas.drawCircle(radius, radius, radius, paint);
//        }
//    }
//}
