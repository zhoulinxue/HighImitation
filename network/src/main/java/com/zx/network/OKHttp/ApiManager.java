package com.zx.network.OKHttp;

import android.content.Context;
import android.support.annotation.NonNull;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import com.xgsb.datafactory.bean.User;
import com.zx.api.api.netWork.NetRequest;
import com.zx.api.api.netWork.NetRequestCallBack;
import com.zx.network.Fileutil;
import com.zx.network.NetBean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Name: ApiManager
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-12 13:30
 */
public class ApiManager {
    private static ApiManager mApiManager;
    private static ApiServer mApiService;
    private static Context mContext;
    private static long NET_TIMEOUT = 15;
    private static long NET_WRITE_TIME_OUT = 10;
    private static long NET_READ_TIMEOUT = 30;

    public ApiManager() {
    }

    public static ApiManager getInstance() {
        if (mApiManager == null) {
            mApiManager = new ApiManager();
        }
        return mApiManager;
    }

    public static ApiServer init(Context context, String baseURL) {
        mContext = context;
        if (mApiService == null) {
            /**日志拦截器*/
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            /**设置超时和拦截器*/
            OkHttpClient client = new OkHttpClient();
            client.setConnectTimeout(NET_TIMEOUT, TimeUnit.SECONDS);
            client.setWriteTimeout(NET_WRITE_TIME_OUT, TimeUnit.SECONDS);
            client.setReadTimeout(NET_READ_TIMEOUT, TimeUnit.SECONDS);
            try {
                client.interceptors().add(new CommonInterceptor());
            } catch (Exception e) {
                e.getMessage();
            }
            client.interceptors().add(logging);
            /**创建Retrofit实例*/
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();
            mApiService = retrofit.create(ApiServer.class);
        }
        return mApiService;
    }

    /**
     * @param params
     * @return
     */
    private Map<String, String> genrateMap(String... params) {
        Map<String, String> map = new HashMap<>();
        if (params.length >= 2) {
            for (int i = 0; i < params.length; i++) {
                if (i % 2 == 0) {
                    map.put(params[i], params[i + 1]);
                }
            }
        }
        return map;
    }

    public NetRequest getRigisterCode(NetRequestCallBack<User> testCallback,String... params) {
        Observable<NetBean<User>> observable = mApiService.getCode(genrateMap(params));
        return new OkHttpRequest<User>(observable, testCallback);
    }

}
