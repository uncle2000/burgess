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
    //    var progressDialog: Dialog? = null
    var tipDialog: QMUITipDialog? = null

    open fun onBackPressed() = false
    //    protected var dialog: Dialog? = null
//    private var globalListeners: MutableList<RxBus.GlobalListener>? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity == null)
            throw ExceptionInInitializerError()
    }

    override fun onDestroy() {
        super.onDestroy()


        // compositeSubscription.clear();
//        if (globalListeners != null) {
//            for (l in globalListeners!!) {
////                RxBus.get().unregisterAll(l)
//            }
//            globalListeners!!.clear()
//        }
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

    override fun onPause() {
        super.onPause()

//        if (globalListeners != null) {
//            for (l in globalListeners!!) {
//                RxBus.get().unregisterAll(l)
//            }
//            globalListeners!!.clear()
//        }
    }

    fun setViewPadding(vararg views: View) {
//        TitleView.setViewPadding(activity, *views)
    }

    fun showProgressDialog() {
        showProgressDialog(true)
    }

    fun showProgressDialog(text: String) {
//        showProgressDialog(false, R.layout.dialog_process, 0, text)
    }

    protected fun showProgressDialog(layoutID: Int) {
        showProgressDialog(true, layoutID)
    }

    fun showProgressDialog(isCancel: Boolean, layoutID: Int) {
        showProgressDialog(isCancel, layoutID, 0, "")
    }

    fun showProgressDialog(image: Int, text: Int) {
        showProgressDialog(false, R.layout.dialog_process, image, getString(text))
    }

    fun showProgressDialog(isCancel: Boolean) {
        showProgressDialog(isCancel, R.layout.dialog_process)
    }

    fun showProgressDialog(isCancel: Boolean, layoutID: Int, image: Int, text: String) {
//        try {
//
//            if (dialog == null || !dialog!!.isShowing) {
//                dialog = ProgressDialog(context)
//                dialog?.show()
//            }
//
//            val loadingView: View = LayoutInflater.from(activity).inflate(layoutID, null, false)
//            dialog?.setContentView(loadingView)
//
//
//            val ivLoading = loadingView.findViewById<ImageView>(R.id.iv_loading)
//            val tvLoading = loadingView.findViewById<TextView>(R.id.tv_loading)
//
//            if (ivLoading != null && image > 0) {
//                ivLoading.setImageResource(image)
//            }
//
//            if (tvLoading != null) {
//                tvLoading.text = text
//            }
//
//            dialog?.setCancelable(isCancel)
//        } catch (ignored: Exception) {
//
//        }

        if (tipDialog == null)
            tipDialog = QMUITipDialog.Builder(context)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("请稍等")
                .create()
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
//        if (activity != null) {
//            showToast(message)
//        }
    }

//    private fun showToast(msg: String?) {
//        if (null == msg || msg.isEmpty())
//            return
//        val inflate = LayoutInflater.from(activity)
//        val toastView: View
//        toastView = inflate.inflate(R.layout.view_toast_middle2, null)
//
//        val toastTv = toastView.findViewById<TextView>(R.id.toast_tv)
//        toastTv.text = msg
//        val toast = Toast(activity)
//        toast.view = toastView
//        toast.duration = Toast.LENGTH_LONG
//        toast.setGravity(Gravity.CENTER, 0, 0)
//
//        toast.show()
//    }

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

    }


    open fun onFragmentResult(requestCode: Int, resultCode: Int, data: Intent?) {

    }

//    fun setResult(resultCode: Int, data: Intent? = null) {
//        activity?.setResult(resultCode, data)
//    }

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
