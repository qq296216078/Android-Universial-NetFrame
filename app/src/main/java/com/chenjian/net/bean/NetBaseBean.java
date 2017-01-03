package com.chenjian.net.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 网络请求解析实体基类。子类必须有无参构造方法
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.13 15:48
 */

abstract public class NetBaseBean {

    /**
     * 子类实现这个方法，在其中解析json
     *
     * @param jsonObject 将要解析的JsonObject
     * @throws JSONException
     */
    abstract public void initByJson(JSONObject jsonObject) throws JSONException;

}
