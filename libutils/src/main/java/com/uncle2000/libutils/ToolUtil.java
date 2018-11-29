package com.uncle2000.libutils;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.media.ExifInterface;
import android.os.Environment;
import android.text.InputFilter;
import android.widget.EditText;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class ToolUtil {

    /**
     * 拍照的图片存储地址
     *
     * @return
     */

    public static String PIC_PATH = "image";
    public static String VOICE_PATH = "voice";

    public static final AtomicInteger counter = new AtomicInteger(0);

    public static void writeLogIntoFile() {

    }

    public static File getSdcardCachedDir(Context context, String dir) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }

        File sdcardDir = Environment.getExternalStorageDirectory();
        File mediaStorageDir = new File(sdcardDir + "/Android/data/" + context.getPackageName(), dir);
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        return mediaStorageDir;
    }

    public static File getOutputVoiceFile(Context context) {
        return getSdcardCacheFileName(context, VOICE_PATH, ".amr");
    }

    public static File getOutputPictureFile(Context context) {
        return getSdcardCacheFileName(context, PIC_PATH, ".jpg");
    }


    public static File getOutputImagePath(Context context) {
        return getSdcardCachedDir(context, PIC_PATH);
    }

    public static File getOutputVoicePath(Context context) {
        return getSdcardCachedDir(context, VOICE_PATH);
    }

    public static File getSdcardCacheFileName(Context context, String dir, String ext) {
        File path = getSdcardCachedDir(context, dir);
        if (path == null) {
            return null;
        }

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date());
        File mediaFile = new File(path, timeStamp + counter.getAndIncrement() + ext);
        return mediaFile;
    }

    /**
     * 只能输入英文，数字，汉字
     *
     * @param str
     * @return 符合规则的字符串
     * 2000追加注释：这里缺少了 常用英文符号和中文符号
     * http://blog.csdn.net/longyi_java/article/details/8268286
     * 上面这个网址我觉得更精确一点
     * 48~57 数字0~9
     * 65~90 大写字母
     * 97~122 小写字母
     * 19968~40869 中文字符（不包括中文符号）
     */
    public static String clearSpecialChar(String str) {
        char[] chars = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            if ((chars[i] >= 48 && chars[i] <= 57)
                    || (chars[i] >= 65 && chars[i] <= 90)
                    || (chars[i] >= 97 && chars[i] <= 122)
                    || (chars[i] >= 19968 && chars[i] <= 40869)) {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    public static void addEditFilter(EditText editText, InputFilter inputFilter) {
        InputFilter[] filters = editText.getFilters();
        InputFilter[] filters2 = new InputFilter[filters.length + 1];
//        for (int i = 0; i < filters.length; i++) {
//            filters2[i] = filters[i];
//        }
        System.arraycopy(filters, 0, filters2, 0, filters.length);
        filters2[filters.length] = inputFilter;
        editText.setFilters(filters2);
    }

    /**
     * 限制文本长度，英文算半个
     *
     * @param s
     * @return
     */
    public static String limitStringLen(String s, int len) {
        if (s == null) {
            return "";
        }
        StringBuilder b = new StringBuilder();
        float total = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c < 128) { // 半角字符
                total += 0.5;
            } else {
                total += 1;
            }

            if (total <= len) {
                b.append(c);
            } else {
                break;
            }
        }
        return b.toString();
    }

    /**
     * 两个整数相除四舍五入并保留整数
     *
     * @param a
     * @param b
     * @return
     */
    public static int toFloat(int a, int b) {
        if (a % b == 0) {
            return a / b * 100;
        } else {
            DecimalFormat df = new DecimalFormat("0.00");
            String str = df.format((float) a / (float) b);
            double d = Double.parseDouble(str);
            return (int) (d * 100);
        }
    }


    /**
     * 实现文本复制功能
     * add by wangqianzhou
     *
     * @param content
     */
    public static void copy(CharSequence content, Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content);
    }


}
