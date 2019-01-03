package com.uncle2000.mylibsuploadcontainer

import android.app.Application
import com.uncle2000.libutils.SharedValueUtils

class App : Application() {
    override fun onCreate() {
        super.onCreate()
//        Density.setDensity(this)

        SharedValueUtils.init(this)
    }
}