package com.zx.mvplibrary;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.zx.api.api.BaseView;
import com.zx.api.app.MvpDialog;

/**
 * Copyright (C),zhx_2018
 * FileName: BaseActivity
 * Author: zhx
 * Date: 2018\10\23 0023 21:43
 * Description: 项目基础类
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    protected Handler mHandler;
    private MvpDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        mDialog = onCreatCustomDialog();
        //设置布局文件
        setContentView(initLayout());
        //初始化view
        onCreateView();
        //获取已保存数据、或者网络请求
        onInitData(savedInstanceState);
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .hideBar(BarHide.FLAG_SHOW_BAR)
                .init();
    }

    /**
     * 初始化 界面布局
     *
     * @return 布局 xml
     */
    protected abstract int initLayout();
    /**
     * 初始化布局
     */
    protected abstract void onCreateView();


    /**
     * 初始化数据
     *
     * @param savedInstanceState 获取已保存的数据
     */
    protected abstract void onInitData(Bundle savedInstanceState);


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoadingDialog() {
        mDialog.show();
    }

    @Override
    public void showToast(String msg) {
        mHandler.post(new ToastRunable(this, msg));
    }

    @Override
    public void dismissLoadingDiaog() {
        mDialog.dissmiss();
    }


    @Override
    public MvpDialog onCreatCustomDialog() {
        return new ImmersionBarDialog(this, R.string.loading_text);
    }

}