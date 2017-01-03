package com.chenjian.net.core.async;

/**
 * 网络请求回调核心类
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.13 17:42
 */

public interface NetListener {

    /**
     * http请求，数据解密部分，成功
     *
     * @param result result
     */
    void sendSuccess(String result);

    /**
     * http请求，数据解密部分，失败
     *
     * @param e e
     */
    void sendError(Exception e);
}
