package com.zx.api.api;

import android.content.Context;

import com.zx.api.app.MvpDialog;

/**
 * Copyright (C), 2015-2018
 * FileName: BaseView
 * Author: zhx
 * Date: 2018\10\23 0023 19:42
 * Description: 基础展示类
 */
public interface BaseView {
    public void showLoadingDialog();

    public void showToast(String msg);

    public void dismissLoadingDiaog();

    public MvpDialog onCreatCustomDialog();

    public void onSuccess(String requestCode, Object object);

    public void onError(String requestCode, String msg);
    public Context getContext();

}
