package com.uncle2000.libviews

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

import com.blankj.utilcode.util.SizeUtils

/**
 * RecycleView 分割线
 */
class RecycleViewDivider(context: Context, orientation: Int) : RecyclerView.ItemDecoration() {
    var isHasBottomDivider = false
    private var mDivider: Drawable? = null
    private var mOrientation: Int = 0
    private var leftPadding: Int = 0
    private var headCount = 0

    init {
        val a = context.obtainStyledAttributes(ATTRS)
        mDivider = a.getDrawable(0)
        a.recycle()
        setOrientation(orientation)
    }


    /**
     * 自定义分割线
     *
     * @param context
     * @param orientation 列表方向
     * @param drawable    分割线图片
     */
    constructor(context: Context, orientation: Int, drawable: Drawable) : this(context, orientation) {
        mDivider = drawable
    }

    constructor(context: Context, orientation: Int, leftPadding: Int) : this(context, orientation) {
        this.leftPadding = SizeUtils.dp2px(leftPadding.toFloat())
    }

    constructor(context: Context, orientation: Int, leftPadding: Int, headCount: Int) : this(context, orientation) {
        this.headCount = headCount
        this.leftPadding = SizeUtils.dp2px(leftPadding.toFloat())
    }

    fun setOrientation(orientation: Int) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw IllegalArgumentException("invalid orientation")
        }
        mOrientation = orientation
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }


    fun drawVertical(c: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount - 1 + 1) {
            val child = parent.getChildAt(i)
            val params = child
                    .layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider!!.intrinsicHeight
            mDivider!!.setBounds(left + leftPadding, top, right, bottom)
            mDivider!!.draw(c)
        }
    }

    fun drawHorizontal(c: Canvas, parent: RecyclerView) {
        val top = parent.paddingTop
        val bottom = parent.height - parent.paddingBottom

        val childCount = parent.childCount
        for (i in 0 until childCount + 1) {
            val child = parent.getChildAt(i)
            val params = child
                    .layoutParams as RecyclerView.LayoutParams
            val left = child.right + params.rightMargin
            val right = left + mDivider!!.intrinsicHeight
            mDivider!!.setBounds(left + leftPadding, top, right, bottom)
            mDivider!!.draw(c)
        }
    }

    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        if (headCount == 0 || itemPosition > headCount) {
            if (mOrientation == VERTICAL_LIST) {
                outRect.set(0, 0, 0, mDivider!!.intrinsicHeight)
            } else {
                outRect.set(0, 0, mDivider!!.intrinsicWidth, 0)
            }
        }
    }

    companion object {
        val HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL
        val VERTICAL_LIST = LinearLayoutManager.VERTICAL
        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }
}