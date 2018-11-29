package com.uncle2000.libviews.webview

import android.content.Context
import android.view.View
//import com.mll.verification.im.chat.sendmsg.SendTool
//import com.mll.verification.share.base.*
import com.uncle2000.libviews.webview.WebViewModel
import java.io.Serializable

/**
 * Created by wangwei on 2018/3/26/
 * 这个文件主要处理
 * 1 加载之前要做某些事情
 * 2 title 右边点击的事情
 */

abstract class ListenerHolder : Serializable, OnTitleClick

//class ShareListenerHolder @JvmOverloads constructor(
//        val shareModel: ShareInterface?,
//        val lifeCycleListener: ShareLifeCycleListener? = null,
//        var autoShow: Boolean? = false,
//        val shareModelTemp: ShareModel? = null
//) : Serializable, ListenerHolder(), InvokeBeforeLoadUrl {
//
//    override fun onRightClick(context: Context, anchor: View) {
//        if (shareModel != null)
//            ShareHandler.createSharePop(context, shareModel, lifeCycleListener, null, anchor)
//        else
//            ShareHandler.createSharePop(context, shareModelTemp!!, lifeCycleListener, null, anchor)
//    }
//
//    override fun initBeforeLoadUrl(context: Context, anchor: View) {
//        if (autoShow == true)
//            onRightClick(context, anchor)
//    }
//}
//
//class SendListenerHolder @JvmOverloads constructor(
//        private val ImSenderBo: ImSenderInterface,
//        private val lifeCycleListener: ShareLifeCycleListener? = null
//) : Serializable, ListenerHolder() {
//    override fun onRightClick(context: Context, anchor: View) {
//        SendTool.sendDefaultMsg(context, ImSenderBo)
//    }
//}

interface InvokeBeforeLoadUrl {
    fun initBeforeLoadUrl(context: Context, anchor: View)
}

interface OnTitleClick {
    fun onRightClick(context: Context, anchor: View)
}

interface WebViewInterface {
    fun getWebViewModel(): WebViewModel
}
