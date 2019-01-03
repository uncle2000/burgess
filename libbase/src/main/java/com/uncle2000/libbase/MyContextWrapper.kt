package com.uncle2000.libbase

import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.LocaleList
import java.util.*

class MyContextWrapper(base: Context) : android.content.ContextWrapper(base) {
    companion object {

        val LANGUAGE_DEFAULT = 0
        val LANGUAGE_CHINA = 1
        val LANGUAGE_HK = 2
        val LANGUAGE_ENGLISH = 3
        var LANGUAGE = "language"

        fun wrap(context: Context, newLocale: Locale): ContextWrapper {
            var context = context

            val res = context.resources
            val configuration = res.configuration

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                configuration.setLocale(newLocale)
                val localeList = LocaleList(newLocale)
                LocaleList.setDefault(localeList)
                configuration.locales = localeList
                context = context.createConfigurationContext(configuration)

            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

                configuration.setLocale(newLocale)
                context = context.createConfigurationContext(configuration)

            }

            return ContextWrapper(context)
        }
    }
}