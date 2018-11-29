//package com.uncle2000.libbase
//
//import android.widget.CheckBox
//
//abstract class SelectableListFragmentKt<T> : ListFragmentNew<T>() {
//    protected var maxSize = 1
//    protected var handleList: MutableList<T> = ArrayList()
//    protected var hideList: MutableList<T>? = null
//    protected var frozenList: Array<T>? = null
//    protected var countListener: SelectCountListener? = null
//
//    /*这里有可能是反选，所以不叫addOne()*/
//    protected fun handleOne(t: T) {
//        handleOne(t, null)
//    }
//
//    protected fun handleOne(t: T, chk: CheckBox?, isReverse: Boolean = true) {
//        handleList.apply {
//            if (maxSize > 1)/*多选*/ {
//                when {
//                    contains(t) -> if (isReverse) remove(t)
//                    size >= maxSize -> criticalNotice()
//                    else -> add(t)
//                }
//
//            } else /*单选*/ {
//                clear()
//                add(t)
//            }
//            countListener?.countNum(handleList.size)
//            adapter?.notifyDataSetChanged()
//        }
//    }
//
//    protected open fun criticalNoticeText() = "最多可以选择${maxSize}个"
//
//    protected open fun criticalNotice() {
//        toast(criticalNoticeText())
//    }
//}
//
//interface SelectCountListener {
//    fun countNum(num: Int)
//}
