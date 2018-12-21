package com.uncle2000.libnet;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uncle2000.libutils.L;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Danger
 * on 2017/9/6.
 */

public class HttpUtil {
    public static final String TAG = "HttpUtil";
    public static final int TIMEOUT = 20000;

    public static <T> T create(String host, Class<T> cls) {
        host = host.startsWith("http") ? host : "http://" + host;

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> L.get(TAG).e(TAG, message)).setLevel(HttpLoggingInterceptor.Level.BODY);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder()
//                            .addHeader("Content-Type", "application/json")
//                            .addHeader("token", SharedValueUtils.INSTANCE.getString(TOKEN, ""))
                            .build();
                    return chain.proceed(request);
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(host)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(cls);
    }

}
