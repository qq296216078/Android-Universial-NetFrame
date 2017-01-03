package com.chenjian.net.listener.common;

/**
 * 返回码
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.15 11:31
 */

public enum CallbackCode {

    /**
     * 网络请求数据成功并解析成功
     */
    CODE_SUCCESS_REQUEST,

    /**
     * 网络请求失败返回本地保存的数据（未实现）
     */
    CODE_SUCCESS_LOCAL,

    /**
     * 网络请求失败，未知错误
     */
    CODE_ERROR_UNKNOWN,

    /**
     * 网络请求失败，返回码不为200
     */
    CODE_ERROR_HTTP_NOT_200,

    /**
     * 网络请求失败，请求过程当中异常了
     */
    CODE_ERROR_REQUEST_EXP,

    /**
     * 服务器返回非成功数据，返回的code非00001
     */
    CODE_ERROR_SERVER_DATA_ERROR,

    /**
     * 解析json出错
     */
    CODE_ERROR_JSON_EXP,
}
