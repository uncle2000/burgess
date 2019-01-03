package com.uncle2000.libutils

import android.content.Context
import android.os.Environment
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

object ToolUtil {

    /**
     * 拍照的图片存储地址
     *
     * @return
     */

    var PIC_PATH = "image"
    var VOICE_PATH = "voice"

    val counter = AtomicInteger(0)

    fun writeLogIntoFile() {

    }

    fun getSdcardCachedDir(context: Context, dir: String): File? {
        val sdStatus = Environment.getExternalStorageState()
        if (sdStatus != Environment.MEDIA_MOUNTED) {
            return null
        }

        val sdcardDir = Environment.getExternalStorageDirectory()
        val mediaStorageDir = File(sdcardDir.toString() + "/Android/data/" + context.packageName, dir)
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null
            }
        }
        return mediaStorageDir
    }

//    fun getOutputVoiceFile(context: Context): File? {
//        return getSdcardCacheFileName(context, VOICE_PATH, ".amr")
//    }
//
//    fun getOutputPictureFile(context: Context): File? {
//        return getSdcardCacheFileName(context, PIC_PATH, ".jpg")
//    }
//
//
//    fun getOutputImagePath(context: Context): File? {
//        return getSdcardCachedDir(context, PIC_PATH)
//    }
//
//    fun getOutputVoicePath(context: Context): File? {
//        return getSdcardCachedDir(context, VOICE_PATH)
//    }

    fun getSdcardCacheFileName(context: Context, dir: String, ext: String): File? {
        val path = getSdcardCachedDir(context, dir) ?: return null

        val timeStamp = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(Date())
        return File(path, timeStamp + counter.getAndIncrement() + ext)
    }
//
//    /**
//     * 只能输入英文，数字，汉字
//     *
//     * @param str
//     * @return 符合规则的字符串
//     * 2000追加注释：这里缺少了 常用英文符号和中文符号
//     * http://blog.csdn.net/longyi_java/article/details/8268286
//     * 上面这个网址我觉得更精确一点
//     * 48~57 数字0~9
//     * 65~90 大写字母
//     * 97~122 小写字母
//     * 19968~40869 中文字符（不包括中文符号）
//     */
//    fun clearSpecialChar(str: String): String {
//        val chars = str.toCharArray()
//        val sb = StringBuffer()
//        for (i in chars.indices) {
//            if (chars[i].toInt() in 48..57
//                    || chars[i].toInt() in 65..90
//                    || chars[i].toInt() in 97..122
//                    || chars[i].toInt() in 19968..40869) {
//                sb.append(chars[i])
//            }
//        }
//        return sb.toString()
//    }
//
//    fun addEditFilter(editText: EditText, inputFilter: InputFilter) {
//        val filters = editText.filters
//        val filters2 = arrayOfNulls<InputFilter>(filters.size + 1)
//        //        for (int i = 0; i < filters.length; i++) {
//        //            filters2[i] = filters[i];
//        //        }
//        System.arraycopy(filters, 0, filters2, 0, filters.size)
//        filters2[filters.size] = inputFilter
//        editText.filters = filters2
//    }
//
//    /**
//     * 限制文本长度，英文算半个
//     *
//     * @param s
//     * @return
//     */
//    fun limitStringLen(s: String?, len: Int): String {
//        if (s == null) {
//            return ""
//        }
//        val b = StringBuilder()
//        var total = 0f
//        for (i in 0 until s.length) {
//            val c = s[i]
//
//            if (c.toInt() < 128) { // 半角字符
//                total += 0.5f
//            } else {
//                total += 1f
//            }
//
//            if (total <= len) {
//                b.append(c)
//            } else {
//                break
//            }
//        }
//        return b.toString()
//    }
//
//    /**
//     * 两个整数相除四舍五入并保留整数
//     *
//     * @param a
//     * @param b
//     * @return
//     */
//    fun toFloat(a: Int, b: Int): Int {
//        if (a % b == 0) {
//            return a / b * 100
//        } else {
//            val df = DecimalFormat("0.00")
//            val str = df.format((a.toFloat() / b.toFloat()).toDouble())
//            val d = java.lang.Double.parseDouble(str)
//            return (d * 100).toInt()
//        }
//    }
//
//
//    /**
//     * 实现文本复制功能
//     * add by wangqianzhou
//     *
//     * @param content
//     */
//    fun copy(content: CharSequence, context: Context) {
//        // 得到剪贴板管理器
//        val cmb = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//        cmb.text = content
//    }


}
