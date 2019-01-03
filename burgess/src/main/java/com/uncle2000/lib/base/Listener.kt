package com.uncle2000.lib.base

import android.view.View


interface OnItemClickListener<T> {
    fun onItemClick(v: View, t: T, pos: Int)
}

interface OnItemLongClickListener<T> {
    fun onItemLongClick(v: View, t: T?, pos: Int)
}