package com.chenjian.net.url;

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
            jsonObject.put("aid", "");
            jsonObject.put("mac", "");
            jsonObject.put("imei", "");
            jsonObject.put("gaid", "");
            jsonObject.put("channel", "");
            jsonObject.put("root", "");
            jsonObject.put("gp", "");
            jsonObject.put("app_ver", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
