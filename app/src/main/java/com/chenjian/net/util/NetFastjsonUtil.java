package com.chenjian.net.util;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 作者： ChenJian
 * 时间： 2016.12.30 20:25
 */

public class NetFastjsonUtil {

    /**
     * 使用fastjson框架解析，因为不需要用到initByJson，所以bean类根本不需要继承NetBaseBean
     *
     * @param aClass     带泛型 T 的类，应该传入xxBeanListener监听器或其的子类。他的父类必须是带泛型T的
     * @param tIndex     泛型 T 所在下标
     * @param jsonString 用来解析的 jsonString
     * @param <T>        泛型 T
     * @return 返回泛型T的实例
     */
    public static <T> T parseItemByFastjson(Class aClass, int tIndex, String jsonString) {
        Type type = ((ParameterizedType) aClass.getGenericSuperclass()).getActualTypeArguments()[tIndex];
        return JSON.parseObject(jsonString, type);
    }
}
