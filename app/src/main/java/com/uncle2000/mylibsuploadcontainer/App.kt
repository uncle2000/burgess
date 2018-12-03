package com.uncle2000.mylibsuploadcontainer

import android.app.Application
import com.uncle2000.libbase.Density
import com.uncle2000.libutils.SharedValueUtils

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Density.setDensity(this)

        Density.setDensity(this)

        SharedValueUtils.init(this)
        com.uncle2000.libutils.App.setInstance(this)
        com.uncle2000.libbase.App.setInstance(this)
        com.uncle2000.libimagecache.App.setInstance(this)
    }
}