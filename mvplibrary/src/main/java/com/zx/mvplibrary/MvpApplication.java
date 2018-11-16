package com.zx.mvplibrary;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.zx.api.api.utils.AppUtils;
import com.zx.pictruemodel.ImageLoader;
import com.zx.pictruemodel.glide.BaseGlideLoader;

/**
 * Copyright (C),zhx_2018
 * FileName: MvpApplication
 * Author: zhx
 * Date: 2018\10\31 0031 16:00
 * Description: ${DESCRIPTION}
 */
public class MvpApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        AppUtils.init(this);
        ImageLoader.init(new BaseGlideLoader());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
