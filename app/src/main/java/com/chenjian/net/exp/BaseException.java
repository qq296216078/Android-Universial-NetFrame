package com.chenjian.net.exp;

/**
 * 自定义通用异常
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.15 10:45
 */

public class BaseException extends Exception {

    private ExceptionCode mCode;

    public BaseException() {

    }

    public BaseException(Exception e) {
        super(e);
    }

    public BaseException(ExceptionCode code) {
        this.mCode = code;
    }

    public ExceptionCode getCode() {
        return mCode;
    }

    public void setCode(ExceptionCode code) {
        this.mCode = code;
    }
}
