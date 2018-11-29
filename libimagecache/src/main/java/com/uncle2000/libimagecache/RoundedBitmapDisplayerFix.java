///*******************************************************************************
// * Copyright 2011-2014 Sergey Tarasevich
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// * http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// *******************************************************************************/
//package com.uncle2000.libimagecache;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapShader;
//import android.graphics.Canvas;
//import android.graphics.ColorFilter;
//import android.graphics.Matrix;
//import android.graphics.Paint;
//import android.graphics.PixelFormat;
//import android.graphics.Rect;
//import android.graphics.RectF;
//import android.graphics.Shader;
//import android.graphics.drawable.Drawable;
//import android.widget.ImageView;
//
//import com.nostra13.universalimageloader.core.assist.LoadedFrom;
//import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
//import com.nostra13.universalimageloader.core.imageaware.ImageAware;
//import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
//
///**
// * Can display bitmap with rounded corners. This implementation works only with ImageViews wrapped
// * in ImageViewAware.
// * <br />
// * This implementation is inspired by
// * <a href="http://www.curious-creature.org/2012/12/11/android-recipe-1-image-with-rounded-corners/">
// * Romain Guy's article</a>. It rounds images using custom drawable drawing. Original bitmap isn't changed.
// * <br />
// * <br />
// * If this implementation doesn't meet your needs then consider
// * <a href="https://github.com/vinc3m1/RoundedImageView">RoundedImageView</a> or
// * <a href="https://github.com/Pkmmte/CircularImageView">CircularImageView</a> projects for usage.
// *
// * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
// * @since 1.5.6
// */
//public class RoundedBitmapDisplayerFix implements BitmapDisplayer {
//
//    protected final int cornerRadius;
//    protected final int margin;
//    ImageView imageView;
//
//    public RoundedBitmapDisplayerFix(ImageView imageView, int cornerRadiusPixels) {
//        this(imageView, cornerRadiusPixels, 0);
//    }
//
//    public RoundedBitmapDisplayerFix(ImageView imageView, int cornerRadiusPixels, int marginPixels) {
//        this.imageView = imageView;
//        this.cornerRadius = cornerRadiusPixels;
//        this.margin = marginPixels;
//    }
//
//    @Override
//    public void display(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
//        if (!(imageAware instanceof ImageViewAware)) {
//            throw new IllegalArgumentException("ImageAware should wrap ImageView. ImageViewAware is expected.");
//        }
//
//        imageAware.setImageDrawable(new RoundedDrawable(imageView, bitmap, cornerRadius, margin));
//    }
//
//    public static class RoundedDrawable extends Drawable {
//
//        protected final float cornerRadius;
//        protected final int margin;
//
//        protected final RectF mRect = new RectF(),
//                mBitmapRect;
//        protected final BitmapShader bitmapShader;
//        protected final Paint paint;
//        ImageView imageView;
//
//        public RoundedDrawable(ImageView imageView, Bitmap bitmap, int cornerRadius, int margin) {
//            this.imageView = imageView;
//            this.cornerRadius = cornerRadius;
//            this.margin = margin;
//
//            bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//            mBitmapRect = new RectF(margin, margin, bitmap.getWidth() - margin, bitmap.getHeight() - margin);
//
//            paint = new Paint();
//            paint.setAntiAlias(true);
//            paint.setShader(bitmapShader);
//            paint.setFilterBitmap(true);
//            paint.setDither(true);
//        }
//
//        @Override
//        protected void onBoundsChange(Rect bounds) {
//            super.onBoundsChange(bounds);
//            mRect.set(margin, margin, bounds.width() - margin, bounds.height() - margin);
//            // Resize the original bitmap to fit the new bound
//            Matrix shaderMatrix = new Matrix();
//            shaderMatrix.setRectToRect(mBitmapRect, mRect, Matrix.ScaleToFit.CENTER);
//
//            final int dwidth = (int) (mBitmapRect.right - mBitmapRect.left);
//            final int dheight = (int) (mBitmapRect.bottom - mBitmapRect.top);
//
//            final int vwidth = imageView.getWidth();
//            final int vheight = imageView.getHeight();
//
//            float scale;
//            float dx = 0, dy = 0;
//
//            if (dwidth * vheight > vwidth * dheight) {
//                scale = (float) vheight / (float) dheight;
//                dx = (vwidth - dwidth * scale) * 0.5f;
//            } else {
//                scale = (float) vwidth / (float) dwidth;
//                dy = (vheight - dheight * scale) * 0.5f;
//            }
//
//            shaderMatrix.setScale(scale, scale);
//            shaderMatrix.postTranslate(Math.round(dx), Math.round(dy));
//
//            bitmapShader.setLocalMatrix(shaderMatrix);
//
//        }
//
//        @Override
//        public void draw(Canvas canvas) {
//            canvas.drawRoundRect(mRect, cornerRadius, cornerRadius, paint);
//        }
//
//        @Override
//        public int getOpacity() {
//            return PixelFormat.TRANSLUCENT;
//        }
//
//        @Override
//        public void setAlpha(int alpha) {
//            paint.setAlpha(alpha);
//        }
//
//        @Override
//        public void setColorFilter(ColorFilter cf) {
//            paint.setColorFilter(cf);
//        }
//    }
//}
