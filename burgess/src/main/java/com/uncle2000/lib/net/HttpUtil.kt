package com.uncle2000.lib.net


import com.google.gson.GsonBuilder
import com.uncle2000.lib.uitils.L
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Danger
 * on 2017/9/6.
 */

object HttpUtil {
    val TAG = "HttpUtil"
    val TIMEOUT = 20000

    fun <T> create(host: String, cls: Class<T>): T {
        var host = host
        host = if (host.startsWith("http")) host else "http://$host"

        val loggingInterceptor = HttpLoggingInterceptor { message -> L.get(TAG).e(TAG, message) }.setLevel(HttpLoggingInterceptor.Level.BODY)

        val gson = GsonBuilder()
                .setLenient()
                .create()

        val client = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                .writeTimeout(TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                            //                            .addHeader("Content-Type", "application/json")
                            //                            .addHeader("token", SharedValueUtils.INSTANCE.getString(TOKEN, ""))
                            .build()
                    chain.proceed(request)
                }
                .build()

        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(host)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create(cls)
    }

}
