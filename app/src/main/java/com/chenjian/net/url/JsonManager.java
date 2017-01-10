package com.chenjian.net.url;

import com.chenjian.net.demo.app.DemoApplication;
import com.chenjian.net.demo.util.PackageUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Json管理类
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.14 11:24
 */
public class JsonManager {

    private static JsonManager mInstance = null;
    private JSONObject mCommonJson = null;
    private boolean mUseCommonJson = true;

    private JsonManager() {

    }

    public static JsonManager getInstance() {
        if (mInstance == null) {
            synchronized (JsonManager.class) {
                if (mInstance == null) {
                    mInstance = new JsonManager();
                }
            }
        }

        return mInstance;
    }

    public JSONObject getCommonJson() {
        return mCommonJson;
    }

    public void setCommonJson(JSONObject commonJson) {
        this.mCommonJson = commonJson;
    }

    public boolean isUseCommonJson() {
        return mUseCommonJson;
    }

    public void setUseCommonJson(boolean useCommonJson) {
        this.mUseCommonJson = useCommonJson;
    }


    /**
     * 返回默认的公共参数
     *
     * @return
     */
    public JSONObject getDefaultCommonJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("app_version_name", PackageUtil.getAppVersionName(DemoApplication.context));
            jsonObject.put("app_version_code", String.valueOf(PackageUtil.getAppVersionCode(DemoApplication.context)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
