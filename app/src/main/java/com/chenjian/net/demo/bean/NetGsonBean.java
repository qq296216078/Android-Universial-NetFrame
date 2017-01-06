package com.chenjian.net.demo.bean;

/**
 * 作者： ChenJian
 * 时间： 2017.1.4 9:42
 */

public class NetGsonBean {
    /**
     * gson要求属性名必须和json字符串里面的key值一样才能解析，找不到对应的key的话，就为null
     */

    private String title;
    private String desc;
    private String date;

    @Override
    public String toString() {
        return "NetGsonBean{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
