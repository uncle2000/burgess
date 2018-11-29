package com.uncle2000.libnet;

/**
 * Created by danger on 16/6/18.
 */

public interface CallBack<T> {

    default void onPreExecute(T t) {
    }

    void onSuccess(T t);

    void onFail(String message, Throwable t);

}
