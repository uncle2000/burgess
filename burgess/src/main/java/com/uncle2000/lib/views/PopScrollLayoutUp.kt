package com.uncle2000.lib.views

import android.annotation.SuppressLint
import android.os.Handler


/**
 * 软件盘的弹出只能把最底部的编辑框顶上去
 * 而这个类的作用是把编辑框下面的其他View也顶上去 这样用户就省去了向上滑的操作
 * modify by 2000 on 2017/12/4.
 */

class PopScrollLayoutUp(private val scrollView: MyScrollView) {
    private val handler = Handler()
    private var timeRemainedToScroll = 1000

    private val runnable = object : Runnable {
        override fun run() {
            handler.removeCallbacks(this)

            timeRemainedToScroll -= 100

            //注意这里 scrollView如果没有子View 一定会崩溃
            val threshold = scrollView.getChildAt(0).height - (scrollView.scrollY + scrollView.height)
            if (threshold > 0) {
                scrollView.scrollBy(0, threshold)
            }

            if (timeRemainedToScroll > 0) {
                handler.postDelayed(this, 100)
            }
        }
    }

    init {
        initPopLayoutUpPara()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initPopLayoutUpPara() {

        scrollToBottom()

//        scrollView.setOnInterceptTouchEventListener(View.OnTouchListener { _, _ ->
//            scrollToBottom()
//            true
//        })
    }

    private fun scrollToBottom() {
        timeRemainedToScroll = 1000
        handler.removeCallbacks(runnable)
        handler.postDelayed(runnable, 100)
    }
}
