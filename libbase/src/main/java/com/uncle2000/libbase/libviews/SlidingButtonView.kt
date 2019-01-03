package com.uncle2000.libbase.libviews

/*
  Created by MJJ on 2015/7/25.
 */

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.HorizontalScrollView
import com.uncle2000.libbase.R

/**
 * 这个是新的滑动删除的view 只有产品展示墙那里在用
 * 解决了3个问题
 * 1 命名“开”“关”相反的问题
 * 2 上下滑动导致卡顿的问题
 * 3 如果文字宽度满屏，水平滑动导致文字换行
 */
class SlidingButtonView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : HorizontalScrollView(context, attrs, defStyleAttr) {
    private var menuLayout: View? = null

    var position: Int = 0
    private var menuWidth: Int = 0

    private var listener: onMenuChanged? = null
    var open: Boolean = false
        private set
    private var once: Boolean = false
    private var slidEnable = true

    init {
        this.overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (!once) {
            menuLayout = findViewById(R.id.menu)
            once = true
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        if (slidEnable && changed) {
            this.scrollTo(0, 0)
            //获取水平滚动条可以滑动的范围，即右侧按钮的宽度
            menuWidth = menuLayout!!.width
        }
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        if (!slidEnable)
            return false
        if (!slidEnable) return super.onTouchEvent(ev)
        val action = ev.action
        when (action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> if (null != listener)
                listener!!.onMenuBeginTouch(position)
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                changeScrollx()
                return true
            }
            else -> {
            }
        }
        return super.onTouchEvent(ev)
    }

    /**
     * 按滚动条被拖动距离判断关闭或打开菜单
     */
    fun changeScrollx() {
        val scrollX = scrollX
        if (scrollX > menuWidth / 2) {
            this.smoothScrollTo(menuWidth, 0)
            open = true
            if (null != listener)
                listener!!.onMenuOpened(position)
        } else {
            this.smoothScrollTo(0, 0)
            open = false
            if (null != listener)
                listener!!.onMenuClosed(position)
        }
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        /*注释掉下面的代码 右侧的menu区域会一起移动*/
        //        if (slidEnable)
        //            menuLayout.setTranslationX(l - menuWidth);
    }

    /**
     * 打开菜单
     */
    fun openMenu() {
        if (!slidEnable)
            return
        if (open!!) {
            return
        }
        this.smoothScrollTo(menuWidth, 0)
        open = true
        if (null != listener)
            listener!!.onMenuOpened(position)
    }

    /**
     * 关闭菜单
     */
    fun closeMenuRightNow() {
        if (!slidEnable)
            return
        if ((!open)!!) {
            return
        }
        /*scrollTo会出现一个问题：有时候会关不住*/
        this.scrollTo(0, 0)
        open = false
        if (null != listener)
            listener!!.onMenuClosed(position)
    }

    fun closeMenu() {
        if (!slidEnable)
            return
        if ((!open)!!) {
            return
        }
        /*smoothScrollTo会出现一个问题：当手指在被打开的item上下拖动list的时候 会失效*/
        this.smoothScrollTo(0, 0)
        open = false
        if (null != listener)
            listener!!.onMenuClosed(position)
    }

    fun setSlidEnable(slidEnable: Boolean) {
        this.slidEnable = slidEnable
    }

    fun setonMenuChangedListener(listener: onMenuChanged) {
        this.listener = listener
    }

    interface onMenuChanged {
        fun onMenuClosed(position: Int)

        fun onMenuOpened(position: Int)

        fun onMenuBeginTouch(position: Int)
    }

}
