package com.uncle2000.libbase

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import android.util.DisplayMetrics
import com.uncle2000.libutils.SharedValueUtils

import java.util.Locale

import android.provider.MediaStore.Video.VideoColumns.LANGUAGE
import com.uncle2000.libbase.MyContextWrapper.Companion.LANGUAGE_CHINA
import com.uncle2000.libbase.MyContextWrapper.Companion.LANGUAGE_DEFAULT
import com.uncle2000.libbase.MyContextWrapper.Companion.LANGUAGE_ENGLISH
import com.uncle2000.libbase.MyContextWrapper.Companion.LANGUAGE_HK

object LanguageUtils {

    //解决了获取系统默认错误的问题
    val setLocale: Locale
        get() {


            val currentLanguage = SharedValueUtils.getInt(LANGUAGE, LANGUAGE_DEFAULT)

            when (currentLanguage) {
                LANGUAGE_DEFAULT -> return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Resources.getSystem().configuration.locales.get(0)
                } else {
                    Locale.getDefault()
                }
                LANGUAGE_CHINA -> return Locale.SIMPLIFIED_CHINESE
                LANGUAGE_HK -> return Locale.TRADITIONAL_CHINESE
                LANGUAGE_ENGLISH -> return Locale.ENGLISH
                else -> return Locale.getDefault()
            }

        }

    @TargetApi(Build.VERSION_CODES.N)
    fun wrapContext(context: Context): Context {
        val resources = context.resources
        val locale = LanguageUtils.setLocale

        val configuration = resources.configuration
        configuration.setLocale(locale)
        val localeList = LocaleList(locale)
        LocaleList.setDefault(localeList)
        configuration.locales = localeList
        return context.createConfigurationContext(configuration)
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun applyChange(context: Context) {
        val res = context.resources
        val dm = res.displayMetrics
        val conf = res.configuration

        val locale = setLocale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            conf.setLocale(locale)
            val localeList = LocaleList(locale)
            LocaleList.setDefault(localeList)
            conf.locales = localeList
        } else {
            conf.locale = locale
            try {
                conf.setLayoutDirection(locale)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        res.updateConfiguration(conf, dm)
    }
}
