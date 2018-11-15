package com.zx.network.OKHttp;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import com.zx.network.NetBean;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Name: ApiManager
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-12 13:30
 */
public class ApiManager<T> {

    private static ApiManager mApiManager;
    private static ApiServer mApiService;
    private static Context mContext;
    private Map<String,Subscription> map;
    private static long NET_TIMEOUT=15;
    private static long NET_WRITE_TIME_OUT=10;
    private static long NET_READ_TIMEOUT=30;

    public ApiManager() {
        map=new HashMap<>();
    }

    public static ApiManager getInstance(){
        if(mApiManager==null){
            mApiManager=new ApiManager();
        }
        return mApiManager;
    }
    public Observable<NetBean<T>> start(String requestCode,String... params){
        Observable<NetBean<T>> observable=null;
        switch (requestCode){
            case ApiServer.MAIN_TEST_URL:
                observable=mApiService.getCode(genrateMap(params));
                break;
        }


      return observable;
    }

    private Map<String,String> genrateMap(String[] params) {
        return new HashMap<>();
    }
    public static ApiServer init(Context context, String baseURL) {
        mContext = context;
        if(mApiService==null){
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

    public Observable<NetBean> getRigisterCode(Map<String,String> map){
        return mApiService.getCode(map);
    };

    /**
     * 移除任务
     * @param tag
     */
    public void remove(String tag) {
        map.remove(tag).unsubscribe();
    }
}
