package com.chenjian.net.exp;

/**
 * http请求返回的StatusCode不为200
 *
 * 作者： ChenJian
 * 时间： 2016.12.15 10:47
 */

public class RespondErrorException extends BaseException {

    public RespondErrorException() {
        super(ExceptionCode.EXPCODE_RESPOND_ERROR);
    }
}
