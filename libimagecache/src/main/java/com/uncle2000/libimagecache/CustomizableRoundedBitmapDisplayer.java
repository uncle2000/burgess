//package com.uncle2000.libimagecache;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapShader;
//import android.graphics.Canvas;
//import android.graphics.ColorFilter;
//import android.graphics.Matrix;
//import android.graphics.Paint;
//import android.graphics.PixelFormat;
//import android.graphics.PorterDuff;
//import android.graphics.PorterDuffXfermode;
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
// * Created by 2000 on 2016/7/28.
// */
//public class CustomizableRoundedBitmapDisplayer implements BitmapDisplayer {
//    protected final int cornerRadius;
//    protected final int margin;
//    ImageView imageView;
//    boolean isLeftTopcornerRound = true, isRightTopcornerRound = true, isLeftBottomcornerRound = true, isRightBottomcornerRound = true;
//
//    public CustomizableRoundedBitmapDisplayer(int cornerRadiusPixels) {
//        this(cornerRadiusPixels, 0);
//    }
//
//    public CustomizableRoundedBitmapDisplayer(ImageView imageView, int cornerRadiusPixels,
//                                              boolean isLeftTopcornerRound, boolean isRightTopcornerRound,
//                                              boolean isLeftBottomcornerRound, boolean isRightBottomcornerRound) {
//        this(cornerRadiusPixels, 0);
//        this.imageView = imageView;
//        this.isLeftTopcornerRound = isLeftTopcornerRound;
//        this.isRightTopcornerRound = isRightTopcornerRound;
//        this.isLeftBottomcornerRound = isLeftBottomcornerRound;
//        this.isRightBottomcornerRound = isRightBottomcornerRound;
//    }
//
//    public CustomizableRoundedBitmapDisplayer(int cornerRadiusPixels, int marginPixels) {
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
//        imageAware.setImageDrawable(new CustomizableRoundedDrawable(imageView, bitmap, margin, cornerRadius, isLeftTopcornerRound, isRightTopcornerRound,
//                isLeftBottomcornerRound, isRightBottomcornerRound));
//    }
//
//    public static class CustomizableRoundedDrawable extends Drawable {
//        protected final float cornerRadius;
//        protected final int margin;
//
//        protected final RectF mRect = new RectF(),
//                mBitmapRect;
//        protected final BitmapShader bitmapShader;
//        protected final Paint paint;
//        boolean isLeftTopcornerRound, isRightTopcornerRound, isLeftBottomcornerRound, isRightBottomcornerRound;
//        RectF mLeftTopRect, mRightTopRect, mLeftBottomRect, mRightBottomRect;
//        ImageView imageView;
//
//        public CustomizableRoundedDrawable(ImageView imageView, Bitmap bitmap, int margin, int cornerRadius,
//                                           boolean isLeftTopcornerRound, boolean isRightTopcornerRound,
//                                           boolean isLeftBottomcornerRound, boolean isRightBottomcornerRound) {
//            this.imageView = imageView;
//            this.isLeftTopcornerRound = isLeftTopcornerRound;
//            this.isRightTopcornerRound = isRightTopcornerRound;
//            this.isLeftBottomcornerRound = isLeftBottomcornerRound;
//            this.isRightBottomcornerRound = isRightBottomcornerRound;
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
//            if (isLeftTopcornerRound) {
//                mLeftTopRect = new RectF();
//                mLeftTopRect.set(margin, margin, margin + cornerRadius, margin + cornerRadius);
//            }
//            if (isRightTopcornerRound) {
//                mRightTopRect = new RectF();
//                mRightTopRect.set(bounds.width() - margin - cornerRadius, margin,
//                        bounds.width() - margin, margin + cornerRadius);
//            }
//            if (isLeftBottomcornerRound) {
//                mLeftBottomRect = new RectF();
//                mLeftBottomRect.set(margin, bounds.height() - margin - cornerRadius,
//                        margin + cornerRadius, bounds.height() - margin);
//            }
//            if (isRightBottomcornerRound) {
//                mRightBottomRect = new RectF();
//                mRightBottomRect.set(bounds.width() - margin - cornerRadius, bounds.height() - margin - cornerRadius,
//                        bounds.width() - margin, bounds.height() - margin);
//            }
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
//            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
//            canvas.drawRoundRect(mRect, cornerRadius, cornerRadius, paint);
//            if (null != mLeftTopRect)
//                canvas.drawRect(mLeftTopRect, paint);
//            if (null != mRightTopRect)
//                canvas.drawRect(mRightTopRect, paint);
//            if (null != mLeftBottomRect)
//                canvas.drawRect(mLeftBottomRect, paint);
//            if (null != mRightBottomRect)
//                canvas.drawRect(mRightBottomRect, paint);
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
