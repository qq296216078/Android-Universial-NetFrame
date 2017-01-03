package com.chenjian.net.demo.app;

import android.app.Application;
import android.content.Context;

import com.chenjian.net.demo.util.PackageUtil;
import com.chenjian.net.url.UrlManager;

import java.util.HashMap;
import java.util.Map;

/**
 * 例子：定义一个Application
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.14 12:26
 */

public class DemoApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        initNet();
    }

    private void initNet() {
        /**
         * 设置网络请求公共参数。注意以下设置的参数为只是get请求的参数
         */
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("app_version_name", PackageUtil.getAppVersionName(context));
        paramMap.put("app_version_code", String.valueOf(PackageUtil.getAppVersionCode(context)));
        UrlManager.getInstance().setCommonParam(paramMap);
    }
}
