package com.zx.api.utils;

import android.util.Log;

/**
 * Name: AppLog
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-12 14:14
 */
public class AppLog {
    private static boolean isDebug=true;

    public static void print(String tag,String msg){
        if(isDebug){
            Log.e(tag,msg);
        }
    }
    public static void d(String tag,String msg){
        if(isDebug){
            Log.d(tag,msg);
        }
    }
    public static void i(String tag,String msg){
        if(isDebug){
            Log.i(tag,msg);
        }
    }
    public static void e(String tag,String msg){
        if(isDebug){
            Log.e(tag,msg);
        }
    }
}
