package com.uncle2000.mylibsuploadcontainer

import android.os.Bundle
import com.uncle2000.libbase.BaseFragmentActivity

class MainActivity : BaseFragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_act)
        showProgressDialog(false, "xxxxx")
        BaseFragmentActivity.go(this, XxFragment::class.java)
    }
}
