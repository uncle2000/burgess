package com.uncle2000.lib.base.timer

import com.uncle2000.lib.uitils.SharedValueUtils


/**
 * 管理app里所有业务上的倒计时和textview联动的类
 * Created by 2000 on 2017/12/21.
 */

class TextTimerManager(private val instanceId: String) {
    public val defaultTotal = (60 * 1000).toLong()

    private var timer: TextTimer? = null

    /* 还有多长时间需要倒计时 */
    public val leftTime: Long
        get() {
            /* 已经持续了多长时间 */
            val now = System.currentTimeMillis()
            if (now == SharedValueUtils.getAny(instanceId, now)) {
                SharedValueUtils.putAny(instanceId, System.currentTimeMillis())
            }
            val continued = now - SharedValueUtils.getAny(instanceId, now) as Long
            return if (continued > defaultTotal) {/* 倒计时已经走完了 就重置 */
                SharedValueUtils.putAny(instanceId, System.currentTimeMillis())
                defaultTotal
            } else {/*否则返回时间差*/
                defaultTotal - continued
            }
        }

    /* 开始 */
    fun start(leftTicks: LeftTicks) {
        timer?.cancel()
        timer = TextTimer(leftTime, leftTicks)
        timer!!.start()
    }

    /* 结束 */
    fun stop() {
        /* 全部回收掉 */
        if (null != timer) {
            timer!!.cancel()
            timer = null//理论上讲 cancel()后 自身应该是要释放的
        }
    }
}

const val ACTIVATE_COUNT_DOWN = "activate"
const val FORGET_PASSWORD_COUNT_DOWN = "forgetPassword"
const val REGISTER_COUNT_DOWN = "register"
