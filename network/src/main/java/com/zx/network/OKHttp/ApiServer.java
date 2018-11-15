package com.zx.network.OKHttp;

import com.xgsb.datafactory.bean.User;
import com.zx.network.NetBean;

import java.util.List;
import java.util.Map;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * Name: ApiService
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-12 13:35
 */
public interface ApiServer<T> {
    public static String MAIN_TEST_URL="main_test";
    //发送验证码手机短信
    @GET(MAIN_TEST_URL)
    Observable<T> getCode(@QueryMap Map<String, String> params);
    @POST("xxx")
    Observable<T> postFormalRegister(@Body User info);
    //用户密码登录
    @GET("xxx")
    Observable<T> getUserInfoByAccount(@QueryMap Map<String, String> params);

    @POST("contacts")
    Observable<T> postContacts(@Body List<User> resp, @QueryMap Map<String, String> params);

}
