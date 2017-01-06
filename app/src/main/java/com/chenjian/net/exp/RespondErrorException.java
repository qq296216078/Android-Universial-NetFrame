package com.chenjian.net.exp;

/**
 * http请求返回的StatusCode不为200
 *
 * 作者： ChenJian
 * 时间： 2016.12.15 10:47
 */

public class RespondErrorException extends BaseException {

    private int mRespondCode;

    public RespondErrorException(int respondCode) {
        super(ExceptionCode.EXPCODE_RESPOND_ERROR);
        this.mRespondCode = respondCode;
    }

    public RespondErrorException() {
        super(ExceptionCode.EXPCODE_RESPOND_ERROR);
    }

    public int getRespondCode() {
        return mRespondCode;
    }

    public void setRespondCode(int respondCode) {
        this.mRespondCode = respondCode;
    }
}
