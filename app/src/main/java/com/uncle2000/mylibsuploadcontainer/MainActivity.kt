package com.uncle2000.mylibsuploadcontainer

import android.os.Bundle
import android.widget.Button
import com.uncle2000.libbase.BaseFragmentActivity
import com.uncle2000.libbase.MyContextWrapper.LANGUAGE_ENGLISH

class MainActivity : BaseFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_act)

        ChangeLanguageHelper.init(this)

        findViewById<Button>(R.id.btn).setOnClickListener {

            ChangeLanguageHelper.changeLanguage(this, LANGUAGE_ENGLISH)

            BaseFragmentActivity.go(this, XxFragment::class.java)

        }
    }
}
