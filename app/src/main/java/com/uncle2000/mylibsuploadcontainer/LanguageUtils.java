package com.uncle2000.mylibsuploadcontainer;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import com.uncle2000.libutils.SharedValueUtils;

import java.util.Locale;

import static android.provider.MediaStore.Video.VideoColumns.LANGUAGE;
import static com.uncle2000.libbase.MyContextWrapper.*;

public class LanguageUtils {

    public static Locale getSetLocale() {


        int currentLanguage = SharedValueUtils.INSTANCE.getInt(LANGUAGE, LANGUAGE_DEFAULT);

        switch (currentLanguage) {
            case LANGUAGE_DEFAULT:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    return Resources.getSystem().getConfiguration().getLocales().get(0);//解决了获取系统默认错误的问题
                } else {
                    return Locale.getDefault();
                }
            case LANGUAGE_CHINA:
                return Locale.SIMPLIFIED_CHINESE;
            case LANGUAGE_HK:
                return Locale.TRADITIONAL_CHINESE;
            case LANGUAGE_ENGLISH:
                return Locale.ENGLISH;
            default:
                return Locale.getDefault();
        }

    }

    @TargetApi(Build.VERSION_CODES.N)
    public static Context wrapContext(Context context) {
        Resources resources = context.getResources();
        Locale locale = LanguageUtils.getSetLocale();

        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        LocaleList localeList = new LocaleList(locale);
        LocaleList.setDefault(localeList);
        configuration.setLocales(localeList);
        return context.createConfigurationContext(configuration);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void applyChange(Context context) {
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();

        Locale locale = getSetLocale();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            conf.setLocale(locale);
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            conf.setLocales(localeList);
        } else {
            conf.locale = locale;
            try {
                conf.setLayoutDirection(locale);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        res.updateConfiguration(conf, dm);
    }
}
