package com.zx.mvplibrary.presenter;

import com.xgsb.datafactory.bean.User;
import com.zx.api.api.BaseView;
import com.zx.api.netWork.NetRequest;
import com.zx.network.OKHttp.BaseRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2015-2018
 * FileName: BasePresenterImpl
 * Author: zhx
 * Date: 2018\10\23 0023 19:41
 * Description: mvp presenter 基类 主要实现 网络请求的 添加及移除逻辑
 */
public abstract class BasePresenterImpl<T extends BaseView> implements BasePresenter{
    List<NetRequest> netRequests = new ArrayList<>();
    protected T mView;

    public BasePresenterImpl(T view) {
        this.mView = view;
    }

    @Override
    public void destory() {
        for (NetRequest netRequest : netRequests) {
            remove(netRequest);
        }
        netRequests.clear();
    }

    protected  NetRequest creatRequest(String requestTag){
        return new BaseRequest<User>(mView,requestTag);
    };

    @Override
    public void doNetWork(String requestTag) {
        NetRequest request=creatRequest(requestTag);
        netRequests.add(request);
    }

    @Override
    public void remove(NetRequest netRequest) {
        if (netRequest != null&&!netRequest.isDestoryed()) {
            netRequest.destory();
        }
    }
}
