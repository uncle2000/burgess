package com.uncle2000.libbase.libviews.dialog

import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import android.widget.TextView
import com.blankj.utilcode.util.SizeUtils
import com.uncle2000.libbase.databinding.DialogFragmentNewBinding
import com.uncle2000.libbase.R

/**
 * 对话框
 * 内容布局为
 * 1.标题（可能为空）
 * 2.内容
 * 3.按钮(1个或者2个）
 * Created by dengjun on 2017/12/15/015.
 */

open class Dialog : DialogFragment(), DialogInterface {
    companion object {
        val EXTRA_PARAMETERS = "parameters"
        val MAX_HEIGHT = 300f  // 单位dp
    }

    // var binding: DialogFragmentNewBinding? = null

    var tvMessage: TextView? = null
    var tvTitle: TextView? = null
    var btnNegative: TextView? = null
    var btnPositive: TextView? = null
    var btns: View? = null
    var btnsDivider: View? = null
    var btnsDivider1: View? = null
    var scrollView: ScrollView? = null

    lateinit var p: DialogParameters
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        p = arguments?.getSerializable(EXTRA_PARAMETERS)!! as DialogParameters

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: DialogFragmentNewBinding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_fragment_new, null, false)

        tvMessage = binding.tvMessage
        tvTitle = binding.tvTitle
        btnNegative = binding.btnNegative
        btnPositive = binding.btnPositive
        btns = binding.btns
        btnsDivider = binding.btnsDivider
        btnsDivider1 = binding.btnsDivider1
        scrollView = binding.scrollView

        applyParameters()
        return binding.root
    }

    open protected fun applyParameters() {

        initTitle(p)
        initMessage(p)
        initButtons(p)
    }


    private fun initTitle(p: DialogParameters) {
        if (TextUtils.isEmpty(p.title)) {
            tvTitle?.visibility = View.GONE
            return
        }
        tvTitle?.visibility = View.VISIBLE
        tvTitle?.text = p.title
    }


    private fun initMessage(p: DialogParameters) {
        tvMessage?.text = p.message
        if (TextUtils.isEmpty(p.title)) { // 无title c7f4
            tvMessage?.setTextColor(ContextCompat.getColor(context!!, R.color.dark_text_color))
        } else { // 有title c6f4
            tvMessage?.setTextColor(ContextCompat.getColor(context!!, R.color.normal_text_color))

            scrollView?.post {

                // 根据message内容 的高度来设置scrollView的高度，如果太高，则滚动
                val maxHeightPx = SizeUtils.dp2px(MAX_HEIGHT)
                scrollView?.layoutParams?.height = Math.min(
                    tvMessage?.height
                        ?: MAX_HEIGHT.toInt(), maxHeightPx
                )

                if (tvMessage?.height ?: MAX_HEIGHT.toInt() > maxHeightPx) {
                    // 把message的padding设置到scrollView上，去掉messageText的padding
                    scrollView?.setPadding(
                        tvMessage?.paddingLeft ?: 0,
                        tvMessage?.paddingTop ?: 0,
                        tvMessage?.paddingRight ?: 0,
                        tvMessage?.paddingBottom ?: 0
                    )

                    tvMessage?.layoutParams?.height = (tvMessage?.height
                        ?: 0) - (tvMessage?.paddingTop ?: 0) - (tvMessage?.paddingBottom ?: 0)
                    tvMessage?.layoutParams = tvMessage?.layoutParams
                    tvMessage?.setPadding(
                        0,
                        0,
                        0,
                        0
                    )


                    scrollView?.layoutParams = scrollView?.layoutParams
                }
            }
        }
    }

    private fun initButtons(p: DialogParameters) {
        val buttonsVisibility = if (!p.haveButton()) View.GONE else View.VISIBLE

        btns?.visibility = buttonsVisibility
        btnsDivider?.visibility = buttonsVisibility

        if (!p.haveButton()) { // 一个按钮都没有
            return
        }

        btnsDivider1?.visibility = if (p.haveAllButton()) View.VISIBLE else View.GONE

        setButton(btnNegative, p.negativeBtnText, p.negativeBtnClickListener, DialogInterface.BUTTON_NEGATIVE)
        setButton(btnPositive, p.positiveBtnText, p.positiveBtnClickListener, DialogInterface.BUTTON_POSITIVE)
    }

    private fun setButton(btn: TextView?, text: CharSequence?, listener: DialogInterface.OnClickListener?, btnID: Int) {
        if (TextUtils.isEmpty(text)) {
            btn?.visibility = View.GONE

        } else {
            btn?.visibility = View.VISIBLE
            btn?.text = text
            btn?.setOnClickListener {
                dismiss()
                listener?.onClick(this, btnID)
            }
        }
    }

    override fun cancel() {
        dismiss()
    }


    fun show(fm: android.support.v4.app.FragmentManager) {
        super.show(fm, "dlg")
    }
}
