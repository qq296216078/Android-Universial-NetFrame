package com.chenjian.net.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： ChenJian
 * 时间： 2016.12.30 20:33
 */

abstract public class NetCommonBean {

    /**
     * 如果要选择性的使用android自带解析，或者otherJson（第三方解析框架，如gson、fastjson）解析的话，
     * 那么子类要继承本类，而不是继承{@link com.chenjian.net.bean.NetBaseBean}，
     * otherJson无法在本类中对自己解析，而initByJson返回类型为void，正是在本类中对自己进行解析，
     * initByJson兼容otherJson会有很别扭的写法，只能在initByJson中使用otherJson解析后再给每个字段赋值
     * <p>
     * 详情请看
     * {@link com.chenjian.net.demo.bean.NetUserBean}
     *
     *
     * 子类重写这个方法时，因为要返回，所以请将返回类型 T 改成具体类型，不然编译会报错
     * 具体使用请看以下两个类
     * {@link com.chenjian.net.demo.bean.NetNewsBean}
     * {@link com.chenjian.net.util.NetCommonBeanUtil}
     *
     * @param jsonObject 将要解析的JsonObject
     * @param <T>        T
     * @return 子类的具体类型
     * @throws JSONException
     */
    abstract public <T extends NetCommonBean> T getBeanByJson(JSONObject jsonObject) throws JSONException;
}
