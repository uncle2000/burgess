package com.uncle2000.libviews.webview.webview

import android.graphics.Bitmap
import android.net.http.SslError
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebView
import com.uncle2000.libviews.StateView
import com.uncle2000.libviews.webview.webview.InvokeOutMethod

/**
 * Created by wangwei on 2018/3/21/
 * 该client处理加载的生命周期
 * 如果要针对特定的页面从h5中拿取数据，那分别写一个client和Fragment的子类去处理就好
 */
open class LibWebViewClient(
        var stateView: StateView,
        var webView: WebView
) : android.webkit.WebViewClient() {
    var invokeOutMethodInterface: InvokeOutMethod? = null

    /**
     * 在点击请求的是链接时才会调用，
     * 重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边。
     */
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        // 使用自己的WebView组件来响应Url加载事件，而不是使用默认浏览器器加载页面
        view.loadUrl(url)
        // 记得消耗掉这个事件。给不知道的朋友再解释一下，Android中返回True的意思就是到此为止,
        // 事件就会不会冒泡传递了，我们称之为消耗掉
        stateView.visibility = View.GONE
        webView.visibility = View.VISIBLE
        return true
    }

    override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
        invokeOutMethodInterface?.showProcessOut()
        stateView.visibility = View.GONE
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView, url: String) {
        invokeOutMethodInterface?.dismissProcessOut()
        super.onPageFinished(view, url)
    }

    /* 在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次。*/
    override fun onLoadResource(view: WebView, url: String) {
        super.onLoadResource(view, url)
    }

    /* 重写此方法可以让webview处理https请求 */
    override fun onReceivedSslError(view: WebView,
                                    handler: SslErrorHandler, error: SslError) {
        super.onReceivedSslError(view, handler, error)
        stateView.visibility = View.VISIBLE
        stateView.showDefaltFailView()
    }

}