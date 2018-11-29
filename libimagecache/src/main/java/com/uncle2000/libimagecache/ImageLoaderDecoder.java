//package com.uncle2000.libimagecache;
//
//import android.graphics.BitmapFactory;
//
//import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
//import com.nostra13.universalimageloader.core.assist.ImageSize;
//import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
//import com.nostra13.universalimageloader.core.decode.ImageDecodingInfo;
//
//import java.io.IOException;
//import java.io.InputStream;
//
///**
// * Created by danger on 16/8/18.
// */
//public class ImageLoaderDecoder extends BaseImageDecoder {
//    /**
//     * @param loggingEnabled Whether debug logs will be written to LogCat. Usually should match {@link
//     *                       ImageLoaderConfiguration.Builder#writeDebugLogs()
//     *                       ImageLoaderConfiguration.writeDebugLogs()}
//     */
//    public ImageLoaderDecoder(boolean loggingEnabled) {
//        super(loggingEnabled);
//    }
//
//    @Override
//    protected BitmapFactory.Options prepareDecodingOptions(ImageSize imageSize, ImageDecodingInfo decodingInfo) {
//        BitmapFactory.Options options = super.prepareDecodingOptions(imageSize, decodingInfo);
//
//        if (decodingInfo.getOriginalImageUri().startsWith("file://")) {
//            options.inSampleSize *= 2;
//        }
//
//        return options;
//    }
//
//    @Override
//    protected ImageFileInfo defineImageSizeAndRotation(InputStream imageStream, ImageDecodingInfo decodingInfo) throws IOException {
//
//        return super.defineImageSizeAndRotation(imageStream, decodingInfo);
//    }
//}
