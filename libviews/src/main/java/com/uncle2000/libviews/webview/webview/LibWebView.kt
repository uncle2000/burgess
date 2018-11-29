package com.uncle2000.libviews.webview.webview

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.annotation.LayoutRes
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View.OnKeyListener
import android.widget.FrameLayout
import com.uncle2000.libviews.R
import com.uncle2000.libviews.databinding.LibWebviewBinding

/**
 * Created by wangwei on 2018/3/21/
 * webview的自定义view  主要用来自定义和抓错误
 */
class LibWebView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {
    private var client: LibWebViewClient
    var libWebViewBinding: LibWebviewBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.lib_webview, this, false)

    init {
        addView(libWebViewBinding.root)

        client = LibWebViewClient(libWebViewBinding.stateView, libWebViewBinding.webView)
        libWebViewBinding.webView.apply {
            webViewClient = client
            webChromeClient = LibWebChromeClient(libWebViewBinding.stateView)
            this.settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                setSupportZoom(true)
                builtInZoomControls = true
                displayZoomControls = false
            }
            //按物理返回键返回上一个html
            setOnKeyListener(OnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_BACK && canGoBack()) {
                    if (canGoBack()) {
                        goBack()
                    } else {
                        client.invokeOutMethodInterface ?: return@OnKeyListener false
                        client.invokeOutMethodInterface!!.finishOut()
                    }
                    return@OnKeyListener true
                }
                false
            })
        }
    }

    fun setInvokeOutMethodInterface(invokeOutMethodInterface: InvokeOutMethod) {
        client.invokeOutMethodInterface = invokeOutMethodInterface
    }

    fun loadUrl(url: String?) {
        libWebViewBinding.webView.loadUrl(url)
    }

//    fun setFaildView(@LayoutRes layout: Int) {
//        libWebViewBinding.stateView.showDefaltFailView()
//    }
}