package com.uncle2000.libutils

import android.os.Looper
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.uncle2000.libbase.App


/**
 * Toast统一管理类
 */
class T private constructor() {

    init {
        /* cannot be instantiated */
        throw UnsupportedOperationException("cannot be instantiated")
    }

    companion object {

        var isShow = true

        /**
         * 短时间显示Toast
         *
         * @param message
         */
        fun showShort(message: CharSequence) {
            try {
                if (isShow && App.getInstance() != null) {

                    val toast = Toast.makeText(App.getInstance(), message, Toast.LENGTH_SHORT)
                    //处理换行文字居中问题
                    try {
                        val view = toast.view as ViewGroup
                        if (view.childCount > 0) {
                            val textView = view.getChildAt(0) as TextView
                            textView.gravity = Gravity.CENTER
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    toast.show()
                }
            } catch (e: Exception) {
                //解决在子线程中调用Toast的异常情况处理
                Looper.prepare()
                Toast.makeText(App.getInstance(), message, Toast.LENGTH_SHORT).show()
                Looper.loop()
            }

        }

        /**
         * 短时间显示Toast
         *
         * @param message
         */
        fun showShort(message: Int) {
            if (isShow)
                Toast.makeText(App.getInstance(), message, Toast.LENGTH_SHORT).show()
        }

        /**
         * 长时间显示Toast
         *
         * @param message
         */
        fun showLong(message: CharSequence) {
            if (isShow)
                Toast.makeText(App.getInstance(), message, Toast.LENGTH_LONG).show()
        }

        /**
         * 长时间显示Toast
         *
         * @param message
         */
        fun showLong(message: Int) {
            if (isShow)
                Toast.makeText(App.getInstance(), message, Toast.LENGTH_LONG).show()
        }

        /**
         * 自定义显示Toast时间
         *
         * @param message
         * @param duration
         */
        fun show(message: CharSequence, duration: Int) {
            if (isShow)
                Toast.makeText(App.getInstance(), message, duration).show()
        }

        /**
         * 自定义显示Toast时间
         *
         * @param message
         * @param duration
         */
        fun show(message: Int, duration: Int) {
            if (isShow)
                Toast.makeText(App.getInstance(), message, duration).show()
        }
    }

}