package com.uncle2000.libbase

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog
import com.trello.rxlifecycle2.components.support.RxFragment

/**
 * 吊炸天的军哥
 * 公元前3000年
 */
abstract class BaseFragment : RxFragment(), View.OnClickListener {
    var tipDialog: QMUITipDialog? = null
    var clickFrequency = 500
    var lastClickTime: Long = 0

//    open fun onBackPressed() = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity == null)
            throw ExceptionInInitializerError()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()

        try {
            val childFragmentManager = Fragment::class.java.getDeclaredField("mChildFragmentManager")
            childFragmentManager.isAccessible = true
            childFragmentManager.set(this, null)

        } catch (ignore: Exception) {

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        hideInput()
    }

    fun showProgressDialog() {
        showProgressDialog(true, "请稍等")
    }

    fun showProgressDialog(text: String) {
        showProgressDialog(true, text)
    }

    fun showProgressDialog(isCancel: Boolean) {
        showProgressDialog(isCancel, "请稍等")
    }

    fun showProgressDialog(isCancel: Boolean, text: String) {
        if (tipDialog == null)
            tipDialog = QMUITipDialog.Builder(context)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord(text)
                .create()
        tipDialog?.setCancelable(isCancel)
        tipDialog?.show()
    }

    fun dismissDialog() {
        if (tipDialog != null) {
            try {
                tipDialog?.dismiss()
            } catch (e: Exception) {
                // e.printStackTrace();
            }

            tipDialog = null
        }
    }

    open fun onBackPressed(activity: Activity): Boolean {
        finish()
        return true
    }

    fun toast(string: Int) {
        toast(getString(string))
    }

    fun toast(message: String?) {
        if (null == message || message.trim { it <= ' ' }.isEmpty()) {
            return
        }
    }

    fun finish() {
        val activity = activity as BaseFragmentActivity? ?: return

        if (activity.supportFragmentManager.backStackEntryCount > 1) {
            activity.popupFragment()
        } else {
            activity.finish()
        }
    }

    //隐藏软键盘
    fun hideInput() {
        activity ?: return
        val view = activity?.window?.peekDecorView()
        if (view != null) {
            val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun showInput(editText: EditText) {
        activity ?: return

        editText.apply {
            isFocusable = true
            isFocusableInTouchMode = true
            requestFocus()
        }
        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(editText, 0)
    }

    override fun onClick(v: View) {
        if (System.currentTimeMillis() - lastClickTime < clickFrequency) {
            return
        } else {
            lastClickTime = System.currentTimeMillis()
        }
    }


    open fun onFragmentResult(requestCode: Int, resultCode: Int, data: Intent?) {

    }

    fun setResult(result: Int) {
        setResult(result, null)
    }

    fun setResult(result: Int, data: Intent?) {
        val activity = activity as BaseFragmentActivity? ?: return

        activity.setFragmentResult(this, result, data)
    }

    /**
     * 注册事件，请在onresume方法注册，因为默认情况下onpause时回取消注册
     *
     * @param eventId
     * @param action1
     * @return
     */
//    fun registerEvent(eventId: Int, action1: RxBus.GlobalListener): RxBus.GlobalListener {
//        val listener = RxBus.get().register(eventId, action1)
//        if (globalListeners == null) {
//            globalListeners = ArrayList<RxBus.GlobalListener>()
//        }
//        globalListeners?.add(listener)
//        return listener
//    }


    fun showFragment(fname: String, args: Bundle?) {
        val activity = activity
        if (activity != null && activity is BaseFragmentActivity) {
            activity.showFragment(fname, args)
        }
    }

    fun showFragment(fname: String, args: Bundle?, reqCode: Int?) {
        val activity = activity
        if (activity != null && activity is BaseFragmentActivity) {
            activity.showFragment(fname, args, reqCode)
        }
    }

    fun popupFragment() {
        val activity = activity
        if (activity != null && activity is BaseFragmentActivity) {
            activity.popupFragment()
        }
    }

    fun pressBack() {
        val activity = activity
        activity?.onBackPressed()
    }
}
