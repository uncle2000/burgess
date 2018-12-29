package com.uncle2000.libviews

import android.app.Activity
import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.drawable.Drawable
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import com.blankj.utilcode.util.ConvertUtils
import com.qmuiteam.qmui.util.QMUIStatusBarHelper
import com.uncle2000.libviews.databinding.ViewTitleBinding

class TitleView @JvmOverloads constructor(
        context: Context,
        private val attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), View.OnClickListener {
    val TYPE_NONE = 0
    val TYPE_WHITE = 1
    val TYPE_BLACK = 2

    var leftDrawable = ContextCompat.getDrawable(context, R.drawable.svg_arrow_left_black)
    var rightDrawable: Drawable? = null
    var right2Drawable: Drawable? = null
    var leftText = ""
    var rightText = ""
    var right2Text = ""
    var titleText = ""
    var subTitleText = ""
    var titleHeight: Int = ConvertUtils.dp2px(48f)
    var leftTextSize: Int = ConvertUtils.dp2px(14f)
    var rightTextSize: Int = ConvertUtils.dp2px(14f)
    var right2TextSize: Int = ConvertUtils.dp2px(14f)
    var titleTextSize: Int = ConvertUtils.dp2px(16f)
    var subTitleTextSize: Int = ConvertUtils.dp2px(15f)
    var leftTextColor = ContextCompat.getColor(context, R.color.defaults_title_text_color)
    var rightTextColor = ContextCompat.getColor(context, R.color.defaults_other_title_text_color)
    var right2TextColor = ContextCompat.getColor(context, R.color.defaults_other_title_text_color)
    var titleTextColor = ContextCompat.getColor(context, R.color.defaults_other_title_text_color)
    var subTitleTextColor = ContextCompat.getColor(context, R.color.defaults_sub_title_text_color)
    //    var backgroundColor: Int = 0
    var dividerColor = ContextCompat.getColor(context, R.color.defaults_divider_color)
    var showBottomDivider = false
    var needFitStatusBar = true
    var colorMode = TYPE_NONE
    var leftVisibility = true
    var rightVisibility = false
    var right2Visibility = false
    var titlePaddingTop = 10

    private val binding: ViewTitleBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_title, this, true)

    init {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        /*放到这里 以便设置编辑框前可以得到参数*/
        initAttrs()

        setLeftOnClickListener(this)
    }

    private fun initAttrs() {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.TitleView)

            for (i in 0 until a.indexCount) {
                val attr = a.getIndex(i)
                when (attr) {
                    R.styleable.TitleView_colorMode -> colorMode = a.getInt(attr, colorMode)
                    R.styleable.TitleView_needFitStatusBar -> needFitStatusBar = a.getBoolean(attr, true)
                    R.styleable.TitleView_titleHeight -> titleHeight = a.getDimension(attr, titleHeight.toFloat()).toInt()
                    R.styleable.TitleView_titlePaddingTop -> titlePaddingTop = a.getDimension(attr, titlePaddingTop.toFloat()).toInt()
                }
            }
            binding.apply {
                if (needFitStatusBar) {
                    val statusBarHeight = QMUIStatusBarHelper.getStatusbarHeight(context)
                    if (statusBarHeight >= 0 && titlePaddingTop >= 0) {
                        QMUIStatusBarHelper.translucent(context as Activity)
                        paddingTopView.layoutParams = ConstraintLayout.LayoutParams(
                                LayoutParams.MATCH_PARENT, titlePaddingTop + statusBarHeight)
                    }
                    root.layoutParams = ConstraintLayout.LayoutParams(LayoutParams.MATCH_PARENT, titleHeight + titlePaddingTop + statusBarHeight)
                } else {
                    if (titlePaddingTop >= 0)
                        paddingTopView.layoutParams = ConstraintLayout.LayoutParams(
                                LayoutParams.MATCH_PARENT, titlePaddingTop)
                    root.layoutParams = ConstraintLayout.LayoutParams(LayoutParams.MATCH_PARENT, titleHeight + titlePaddingTop)
                }
                when (colorMode) {
                    TYPE_WHITE -> {
                        if (needFitStatusBar)
                            QMUIStatusBarHelper.setStatusBarDarkMode(context as Activity)
                        leftDrawable = ContextCompat.getDrawable(context, R.drawable.svg_arrow_left_white)
                        leftTextColor = ContextCompat.getColor(context, R.color.defaults_white_text_color)
                        rightTextColor = ContextCompat.getColor(context, R.color.defaults_white_text_color)
                        right2TextColor = ContextCompat.getColor(context, R.color.defaults_white_text_color)
                        titleTextColor = ContextCompat.getColor(context, R.color.defaults_white_text_color)
                        subTitleTextColor = ContextCompat.getColor(context, R.color.defaults_white_text_color)
                    }
                    TYPE_BLACK -> {
                        if (needFitStatusBar)
                            QMUIStatusBarHelper.setStatusBarLightMode(context as Activity)
                        leftDrawable = ContextCompat.getDrawable(context, R.drawable.svg_arrow_left_black)
                        leftTextColor = ContextCompat.getColor(context, R.color.defaults_title_text_color)
                        rightTextColor = ContextCompat.getColor(context, R.color.defaults_other_title_text_color)
                        right2TextColor = ContextCompat.getColor(context, R.color.defaults_other_title_text_color)
                        titleTextColor = ContextCompat.getColor(context, R.color.defaults_other_title_text_color)
                        subTitleTextColor = ContextCompat.getColor(context, R.color.defaults_sub_title_text_color)
                    }
                    else -> {
                    }
                }
            }
            for (i in 0 until a.indexCount) {
                val attr = a.getIndex(i)
                when (attr) {
                    R.styleable.TitleView_leftDrawable -> leftDrawable = a.getDrawable(attr)
                    R.styleable.TitleView_rightDrawable -> rightDrawable = a.getDrawable(attr)
                    R.styleable.TitleView_rightDrawable2 -> right2Drawable = a.getDrawable(attr)
                    R.styleable.TitleView_leftText -> leftText = a.getString(attr)
                    R.styleable.TitleView_rightText -> rightText = a.getString(attr)
                    R.styleable.TitleView_right2Text -> right2Text = a.getString(attr)
                    R.styleable.TitleView_titleText -> titleText = a.getString(attr)
                    R.styleable.TitleView_subTitleText -> subTitleText = a.getString(attr)
                    R.styleable.TitleView_leftTextSize -> leftTextSize = a.getDimensionPixelSize(attr, leftTextSize)
                    R.styleable.TitleView_rightTextSize -> rightTextSize = a.getDimensionPixelSize(attr, rightTextSize)
                    R.styleable.TitleView_right2TextSize -> right2TextSize = a.getDimensionPixelSize(attr, right2TextSize)
                    R.styleable.TitleView_titleTextSize -> titleTextSize = a.getDimensionPixelSize(attr, titleTextSize)
                    R.styleable.TitleView_subTitleTextSize -> subTitleTextSize = a.getDimensionPixelSize(attr, subTitleTextSize)
                    R.styleable.TitleView_leftTextColor -> leftTextColor = a.getColor(attr, leftTextColor)
                    R.styleable.TitleView_rightTextColor -> rightTextColor = a.getColor(attr, rightTextColor)
                    R.styleable.TitleView_right2TextColor -> right2TextColor = a.getColor(attr, right2TextColor)
                    R.styleable.TitleView_titleTextColor -> titleTextColor = a.getColor(attr, titleTextColor)
                    R.styleable.TitleView_subTitleTextColor -> subTitleTextColor = a.getColor(attr, subTitleTextColor)
//                    R.styleable.TitleView_backgroundColor -> backgroundColor = a.getColor(attr, )
                    R.styleable.TitleView_dividerColor -> dividerColor = a.getColor(attr, dividerColor)
                    R.styleable.TitleView_showBottomDivider -> showBottomDivider = a.getBoolean(attr, false)
                    R.styleable.TitleView_leftVisibility -> leftVisibility = a.getBoolean(attr, true)
                    R.styleable.TitleView_rightVisibility -> rightVisibility = a.getBoolean(attr, false)
                    R.styleable.TitleView_right2Visibility -> right2Visibility = a.getBoolean(attr, false)
                }
            }
            a.recycle()
        }

        binding.apply {
            titleLeft.visibility = if (leftVisibility)
                View.VISIBLE
            else
                View.GONE
            titleRight.visibility = if (rightVisibility)
                View.VISIBLE
            else
                View.GONE
            titleRight2.visibility = if (right2Visibility)
                View.VISIBLE
            else
                View.GONE
            titleLeftIv.setImageDrawable(leftDrawable)
            titleRightIv.setImageDrawable(rightDrawable)
            titleRightIv2.setImageDrawable(right2Drawable)
            titleLeftTv.text = leftText
            titleLeftTv.setTextColor(leftTextColor)
            titleLeftTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize.toFloat())
            titleRightTv.text = rightText
            titleRightTv.setTextColor(rightTextColor)
            titleRightTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize.toFloat())
            titleRightTv2.text = right2Text
            titleRightTv2.setTextColor(right2TextColor)
            titleRightTv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, right2TextSize.toFloat())
            title.text = titleText
            title.setTextColor(titleTextColor)
            title.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize.toFloat())
            subTitle.text = subTitleText
            subTitle.setTextColor(subTitleTextColor)
            subTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, subTitleTextSize.toFloat())
            divider.setBackgroundColor(dividerColor)
            divider.visibility = if (showBottomDivider)
                View.VISIBLE
            else
                View.GONE
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.title_left) {
            // 内容已经全部被隐藏
            if (binding.titleLeft.visibility != View.VISIBLE && binding.titleLeftTv.visibility != View.VISIBLE) {
                return
            }
            if (context is Activity) {
                (context as Activity).onBackPressed()
            }
        }
    }

    fun setLeftOnClickListener(listener: View.OnClickListener) {
        binding.titleLeft.setOnClickListener(listener)
    }

    @JvmOverloads
    fun setTitleContent(titleText: String = "", leftTitleText: String = "", rightTitleText: String = "", subTitleText: String = "") {
        if (titleText.isNotBlank()) {
            binding.title.text = titleText
        }
        if (leftTitleText.isNotBlank()) {
            binding.titleLeftTv.text = leftTitleText
        }
        if (rightTitleText.isNotBlank()) {
            binding.titleRightTv.text = rightTitleText
        }
        if (subTitleText.isNotBlank()) {
            binding.subTitle.text = subTitleText
        }
    }
}