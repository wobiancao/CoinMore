package com.morecoin.app;

import android.app.Application;

import com.pgyersdk.crash.PgyCrashManager;
import com.umeng.commonsdk.UMConfigure;


public class MyApplication extends Application {
    private final String UMENG_KEY = "5a4b29548f4a9d3009000025";
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        PgyCrashManager.register(this);
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, UMENG_KEY);
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
