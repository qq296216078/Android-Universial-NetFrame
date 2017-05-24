package com.chenjian.net.request;

/**
 * 作者：chenjian
 * 日期：2017/5/24 14:24
 * 个人博客地址：http://blog.csdn.net/qq_296216078
 */

public interface HttpEngine {
    public String get(String url) throws Exception;

    public String post(String url, String param) throws Exception;
}
