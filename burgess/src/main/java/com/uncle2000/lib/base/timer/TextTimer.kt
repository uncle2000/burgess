package com.uncle2000.lib.base.timer

import android.os.CountDownTimer

/**
 * Created by 2000 on 2017/12/4.
 */

class TextTimer : CountDownTimer {
    private var leftTicks: LeftTicks? = null

    constructor(millisInFuture: Long, leftTicks: LeftTicks) : super(millisInFuture,
        defaultFrequency
    ) {
        this.leftTicks = leftTicks
    }

    constructor(millisInFuture: Long, countDownInterval: Long, leftTicks: LeftTicks) : super(millisInFuture, countDownInterval) {
        this.leftTicks = leftTicks
    }

    override fun onTick(l: Long) {
        /* 如果这里不减1 会导致数字不连续 因为该方法并不是绝对的1000毫秒才执行到这里 */
        leftTicks?.getLeftTicks(l - 1)
    }

    override fun onFinish() {
        leftTicks?.getLeftTicks(0L)
    }

    companion object {
        private val defaultFrequency: Long = 1000
    }

}
