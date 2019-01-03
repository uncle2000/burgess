package com.uncle2000.libbase

import android.content.Context
import android.content.res.Resources
import android.os.Build
import com.uncle2000.libutils.SharedValueUtils

import java.util.Locale

import com.uncle2000.libbase.MyContextWrapper.Companion.LANGUAGE

object ChangeLanguageHelper {


    private val mLanguage = ""

    private var mResources: Resources? = null

    private var mAutoCountry: String? = null


    fun init(context: Context) {
        //        mResources = context.getResources();
        initResources(context)
        //        int currentLanguage = SharedValueUtils.INSTANCE.getInt(LANGUAGE, LANGUAGE_DEFAULT);

        //        switch (currentLanguage) {
        //            case LANGUAGE_DEFAULT:
        //                country = context.getResources().getConfiguration().locale.getCountry();
        //                if ("TW".equals(country) || "HK".equals(country) || "MO".equals(country)) {
        //                    country = "CN";
        //                }
        //                if ("CN".equals(country)) {
        //                    mLanguage = "zh-CN";
        //                } else if ("US".equals(country)) {
        //                    mLanguage = "en";
        //                }
        //                break;
        //            case LANGUAGE_CHINA:
        //                mLanguage = "zh-CN";
        //                break;
        //            case LANGUAGE_ENGLISH:
        //                mLanguage = "en";
        //                break;
        //            default:
        //                country = context.getResources().getConfiguration().locale.getCountry();
        //                if ("CN".equals(country)) {
        //                    mLanguage = "zh-CN";
        //                } else if ("US".equals(country)) {
        //                    mLanguage = "en";
        //                }
        //                break;
        //        }

        mAutoCountry = Locale.getDefault().country
    }

    //    /**
    //     * 获取当前字符串资源的内容
    //     */
    //    public static String getStringById(int id) {
    //        String string;
    //        if (mLanguage != null && !"".equals(mLanguage)) {
    //            string = mResources.getString(id, mLanguage);
    //        } else {
    //            string = mResources.getString(id, "");
    //        }
    //
    //        if (string != null && string.length() > 0) {
    //            return string;
    //        }
    //        return "";
    //    }

    fun changeLanguage(context: Context, language: Int) {
        //        switch (language) {
        //            case LANGUAGE_CHINA:
        //                mLanguage = "zh-rCN";
        //                SharedValueUtils.INSTANCE.putAny(LANGUAGE, LANGUAGE_CHINA);
        //                break;
        //            case LANGUAGE_ENGLISH:
        //                mLanguage = "en";
        //                SharedValueUtils.INSTANCE.putAny(LANGUAGE, LANGUAGE_ENGLISH);
        //                break;
        //            case LANGUAGE_HK:
        //                mLanguage = "zh-rHK";
        //                SharedValueUtils.INSTANCE.putAny(LANGUAGE, LANGUAGE_HK);
        //                break;
        //            case LANGUAGE_DEFAULT:
        //                break;
        //        }
        SharedValueUtils.putAny(LANGUAGE, language)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            LanguageUtils.applyChange(context)
        }
    }

    fun initResources(context: Context) {
        mResources = context.resources
    }
}
