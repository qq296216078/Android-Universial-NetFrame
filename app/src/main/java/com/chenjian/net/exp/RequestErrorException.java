package com.chenjian.net.exp;

/**
 * http请求时发生异常
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.15 10:59
 */

public class RequestErrorException extends BaseException {

    public RequestErrorException(Exception e) {
        super(e);
    }

    public RequestErrorException() {
        super(ExceptionCode.EXPCODE_REQUEST_ERROR);
    }
}
