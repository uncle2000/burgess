package com.uncle2000.lib.imagecache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

public class ImageInterface {

    public static void displayHeadImage(Context context, ImageView view, String url, int errorDrawable) {
        RequestOptions mRequestOptions = RequestOptions
                .centerCropTransform()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(errorDrawable)
                .placeholder(errorDrawable)
                .skipMemoryCache(false);
        Glide.with(context).load(url).apply(mRequestOptions).into(view);

//        Glide.with(context).load(url).centerCrop().placeholder(errorDrawable).diskCacheStrategy(DiskCacheStrategy.ALL).into(view);
    }

    //    @BindingAdapter({"imageUrl"})
    public static void displayImage(Context context, ImageView view, String url, int errorDrawable) {
        view.setTag(null);
        RequestOptions mRequestOptions = RequestOptions
                .centerInsideTransform()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(errorDrawable)
                .placeholder(errorDrawable)
                .skipMemoryCache(false);
        Glide.with(context).load(url).apply(mRequestOptions).into(view);
    }

    public static void displayImage(Context context, String url, int errorDrawable, Target<Drawable> target) {
        RequestOptions mRequestOptions = RequestOptions
                .centerInsideTransform()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(errorDrawable)
                .placeholder(errorDrawable)
                .skipMemoryCache(false);
        Glide.with(context).load(url).apply(mRequestOptions).into(target);
    }


    public static void displayImage(Context context, ImageView view, byte[] bitmap, int errorDrawable) {
        RequestOptions mRequestOptions = RequestOptions
                .centerCropTransform()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(errorDrawable)
                .placeholder(errorDrawable)
                .skipMemoryCache(false);
        Glide.with(context).load(bitmap).apply(mRequestOptions).into(view);
//        Glide.with(context).load(bitmap).into(view);
    }

    public static void displayImage(Context context, ImageView view, Bitmap bitmap, int errorDrawable) {
        view.setTag(null);
        RequestOptions mRequestOptions = RequestOptions
                .centerCropTransform()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .error(errorDrawable)
                .placeholder(errorDrawable)
                .skipMemoryCache(false);
        Glide.with(context).load(bitmap).apply(mRequestOptions).into(view);
//        Glide.with(context).load(bitmap).into(view);
    }
}
