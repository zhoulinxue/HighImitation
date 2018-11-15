package com.zx.network.OKHttp;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Response;
import com.zx.api.utils.AppLog;

/**
 * Name: CommonInterceptor
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-12 13:45
 */
public class CommonInterceptor implements Interceptor {
    private String netWork="app_net_suc";
    @Override
    public Response intercept(Chain chain) {
        try {
            Response response = chain.proceed(chain.request());
            Response s = response.networkResponse();
            AppLog.print(netWork,response.code()+" : "+chain.request().urlString());
            return response;
        } catch (Exception i) {
            return null;
        }
    }
}
