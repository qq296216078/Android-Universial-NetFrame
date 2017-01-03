package com.chenjian.net.exp;

/**
 * 自定义异常码
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.15 10:54
 */

public enum ExceptionCode {
    /**
     * http请求的过程中发生了异常
     */
    EXPCODE_REQUEST_ERROR,


    /**
     * http请求返回的状态码不为200
     */
    EXPCODE_RESPOND_ERROR,
}
