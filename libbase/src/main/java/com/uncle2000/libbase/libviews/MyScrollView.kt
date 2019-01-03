//package com.uncle2000.libviews
//
//import android.content.Context
//import android.util.AttributeSet
//import android.view.MotionEvent
//import android.view.View
//import android.widget.ScrollView
//
///**
// * Created by Administrator on 2017/5/12.
// */
//
//class MyScrollView : ScrollView {
//    internal var onInterceptTouchEventListener: View.OnTouchListener? = null
//
//    constructor(context: Context) : super(context) {}
//
//    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}
//
//    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}
//
//    fun setOnInterceptTouchEventListener(onInterceptTouchEventListener: View.OnTouchListener) {
//        this.onInterceptTouchEventListener = onInterceptTouchEventListener
//    }
//
//    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
//        if (onInterceptTouchEventListener != null) {
//            onInterceptTouchEventListener!!.onTouch(this, ev)
//        }
//        return super.onInterceptTouchEvent(ev)
//    }
//}
