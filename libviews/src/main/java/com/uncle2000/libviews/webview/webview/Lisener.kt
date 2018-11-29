package com.uncle2000.libviews.webview.webview

/**
 * wangwei
 * 2018.4.8
 * webview内需要调到外部的一些方法
 */

interface InvokeOutMethod {
    /*调用外部的finish()*/
    fun finishOut()

    fun showProcessOut()

    fun dismissProcessOut()
}
