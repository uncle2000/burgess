package com.uncle2000.libviews.webview.webview

import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.uncle2000.libviews.StateView

/**
 * Created by wangwei on 2018/3/26/
 * 如你所见 这里处理生命周期成功结束，但是所得并不是我们想要的状态的情况
 * !!这里没法管到所有情况
 */

open class LibWebChromeClient(val stateView: StateView) : WebChromeClient() {
    override fun onReceivedTitle(view: WebView, title: String) {
        val pnotfound = "404"
        if (title.contains(pnotfound) || title.contains("找不到网页")
                || title.contains("网页无法打开")) {
            stateView.visibility = View.VISIBLE
            stateView.state = StateView.STATE_FAIL
        } else {
            stateView.visibility = View.GONE
        }
    }
}