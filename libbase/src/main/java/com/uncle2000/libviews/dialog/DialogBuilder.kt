package com.uncle2000.libviews.dialog

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.FragmentManager

/**
 * Created by dengjun on 2017/12/15/015.
 */
open class DialogBuilder {
    var context: Context

    constructor(context: Context) {
        this.context = context
    }

    val p = DialogParameters()

    fun setTitle(@StringRes titleId: Int): DialogBuilder {
        p.title = context.resources.getString(titleId)
        return this
    }

    fun setTitle(title: CharSequence): DialogBuilder {
        p.title = title
        return this
    }

    fun setMessage(@StringRes messageId: Int): DialogBuilder {
        p.message = context.resources.getString(messageId)
        return this
    }

    fun setMessage(message: CharSequence): DialogBuilder {
        p.message = message
        return this
    }

    fun setPositiveButton(@StringRes textId: Int, listener: DialogInterface.OnClickListener?): DialogBuilder {
        p.positiveBtnText = context.resources.getString(textId)
        p.positiveBtnClickListener = listener
        return this
    }


    fun setPositiveButton(text: CharSequence, listener: DialogInterface.OnClickListener?): DialogBuilder {
        p.positiveBtnText = text
        p.positiveBtnClickListener = listener
        return this
    }


    fun setNegativeButton(@StringRes textId: Int, listener: DialogInterface.OnClickListener?): DialogBuilder {
        p.negativeBtnText = context.resources.getString(textId)
        p.negativeBtnClickListener = listener
        return this
    }

    fun setNegativeButton(text: CharSequence, listener: DialogInterface.OnClickListener?): DialogBuilder {
        p.negativeBtnText = text
        p.negativeBtnClickListener = listener
        return this
    }


    fun setCancelable(cancelable: Boolean): DialogBuilder {
        p.cancelable = cancelable
        return this
    }

    fun setOnCancelListener(onCancelListener: DialogInterface.OnCancelListener): DialogBuilder {
        p.onCancelListener = onCancelListener
        return this
    }

    fun setOnDismissListener(onDismissListener: DialogInterface.OnDismissListener): DialogBuilder {
        p.onDismissListener = onDismissListener
        return this
    }

    fun setInitNumber(i: Int): DialogBuilder {
        p.initNumber = i
        return this
    }

    fun setMinNumber(i: Int): DialogBuilder {
        p.minNumber = i
        return this
    }

    fun setMaxNumber(i: Int): DialogBuilder {
        p.maxNumber = i
        return this
    }

    fun create(): Dialog {
        val dialog = Dialog()
        val arguments = Bundle()

        arguments.putSerializable(Dialog.EXTRA_PARAMETERS, p)
        dialog.arguments = arguments

        return dialog
    }

//    fun createInputDialog(): Dialog {
//        val dialog = DialogWithInput()
//        val arguments = Bundle()
//        dialog.etContent
//        arguments.putSerializable(Dialog.EXTRA_PARAMETERS, p)
//        dialog.arguments = arguments
//        return dialog
//    }

    fun show(fm: FragmentManager?): Dialog {
        fm ?: return create()
        val dialog = create()
        dialog.show(fm)
        return dialog
    }
}

