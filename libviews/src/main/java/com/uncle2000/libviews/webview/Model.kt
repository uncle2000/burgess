package com.uncle2000.libviews.webview

//import com.uncle2000.libviews.webview.ListenerHolder
import java.io.Serializable

/**
 * wangwei
 * 2018.4.8
 */
const val MODEL = "model"

class WebViewModel(
        /* url必填 */
        var url: String,
        /* title为null则不显示TitleBar */
        var title: String? = null
        /* 这个属性只管实现title右上角是分享还是发送 */
//        var listenerHolder: ListenerHolder? = null
) : Serializable
