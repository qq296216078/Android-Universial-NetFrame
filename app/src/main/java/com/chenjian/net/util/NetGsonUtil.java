package com.chenjian.net.util;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 作者： ChenJian
 * 时间： 2016.12.30 20:25
 */

public class NetGsonUtil {

    /**
     * 使用gson框架解析，因为不需要用到initByJson，所以bean类根本不需要继承NetBaseBean
     *
     * @param aClass     带泛型 T 的类，应该传入xxBeanListener监听器或其的子类。他的父类必须是带泛型T的
     * @param tIndex     泛型 T 所在下标
     * @param jsonString 用来解析的 jsonString
     * @param <T>        泛型 T
     * @return 返回泛型T的实例
     */
    public static <T> T parseItemByGson(Class aClass, int tIndex, String jsonString) {
        Type type = ((ParameterizedType) aClass.getGenericSuperclass()).getActualTypeArguments()[tIndex];
        return new Gson().fromJson(jsonString, type);
    }
}
