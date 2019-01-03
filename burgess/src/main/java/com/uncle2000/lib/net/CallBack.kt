package com.uncle2000.lib.net

/**
 * Created by danger on 16/6/18.
 */

interface CallBack<T> {

    fun onPreExecute(t: T) {}

    fun onSuccess(t: T?)

    fun onFail(message: String?, t: Throwable?)

}
