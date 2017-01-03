package com.chenjian.net.url;

import com.chenjian.net.demo.app.DemoApplication;
import com.chenjian.net.demo.util.PackageUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Url管理类
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.14 11:24
 */
public class UrlManager {

    private static UrlManager mInstance = null;
    private Map<String, String> mCommonParam = null;
    private boolean mUseCommonParam = true;

    private UrlManager() {

    }

    public static UrlManager getInstance() {
        if (mInstance == null) {
            synchronized (UrlManager.class) {
                if (mInstance == null) {
                    mInstance = new UrlManager();
                }
            }
        }

        return mInstance;
    }

    public Map<String, String> getCommonParam() {
        return mCommonParam;
    }

    public void setCommonParam(Map<String, String> commonParam) {
        this.mCommonParam = commonParam;
    }

    public boolean isUseCommonParam() {
        return mUseCommonParam;
    }

    public void setUseCommonParam(boolean useCommonParam) {
        this.mUseCommonParam = useCommonParam;
    }


    /**
     * 返回默认的公共参数
     *
     * @return
     */
    public Map<String, String> getDefaultCommonParam() {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("app_version_name", PackageUtil.getAppVersionName(DemoApplication.context));
        paramMap.put("app_version_code", String.valueOf(PackageUtil.getAppVersionCode(DemoApplication.context)));
        return paramMap;
    }
}
