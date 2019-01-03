package com.uncle2000.lib.views.dialog

import android.content.DialogInterface
import android.text.TextUtils
import java.io.Serializable

/**
 * Created by dengjun on 2017/12/15/015.
 */

class DialogParameters : Serializable {
    var title: CharSequence? = null
    var message: CharSequence? = null
    var positiveBtnText: CharSequence? = null
    var negativeBtnText: CharSequence? = null

    var onCancelListener: DialogInterface.OnCancelListener? = null
    var onDismissListener: DialogInterface.OnDismissListener? = null

    var positiveBtnClickListener: DialogInterface.OnClickListener? = null
    var negativeBtnClickListener: DialogInterface.OnClickListener? = null

    var cancelable: Boolean = true

    // 数字输入框专用
    var initNumber: Int? = null
    var minNumber: Int? = null
    var maxNumber: Int? = null


    fun haveButton() = !TextUtils.isEmpty(negativeBtnText) || !TextUtils.isEmpty(positiveBtnText)
    fun haveAllButton() = !TextUtils.isEmpty(negativeBtnText) && !TextUtils.isEmpty(positiveBtnText)
}