package com.zx.mvplibrary;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

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
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
