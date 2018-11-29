//package com.uncle2000.libimagecache.imagetool;
//
//public class ImageTool {
//
//    static {
//        System.loadLibrary("MllIMageCompress");
//    }
//
//    /**
//     * @param context            上下文，没有实质性用处，只用于防止别人盗用此库
//     * @param oriFileName        输入的jpeg文件名
//     * @param compressedFileName 压缩过的图片文件名
//     * @param quality            图片质量，如果传<=0由底层自动控制，>100则理解为100
//     * @param scale              缩放比例
//     * @return 0 成功，其它，失败，
//     */
//    public native static int compressImage(Object context, String oriFileName, String compressedFileName, int quality,
//                                           float scale, int degree);
//
//    public native static int compressBmpFileToJpgFile(Object context, String inBmpFileName, String outJpgFileName, int quality);
//}
