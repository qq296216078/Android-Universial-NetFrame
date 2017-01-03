package com.chenjian.net.bean;

import com.chenjian.net.listener.common.CallbackCode;

import java.util.Map;

/**
 * 网络请求返回实体类
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.14 16:02
 */

public class NetRetBean {

    /**
     * 返回码，具体说明请看{@link com.chenjian.net.listener.common.CallbackCode}
     */
    private CallbackCode mCallbackCode;

    /**
     * 请求过程中发生异常，包括请求时，解析时。可能为null
     */
    private Exception mException;

    /**
     * 服务端的返回码，是服务端返回的数据中解析“code”字段得到的。可能为null
     */
    private String mServerCode;

    /**
     * 服务端返回的消息，是服务端返回的数据中解析“message”字段得到的。可能为null
     */
    private String mServerMsg;

    /**
     * 服务端返回的时间，是服务端返回的数据中解析“time”字段得到的。可能为null
     */
    private String mServerTime;

    /**
     * 服务端返回的数据，是服务端返回的数据中解析“data”字段得到的。可能为null
     */
    private String mServerData;

    /**
     * 服务端返回的数据解析成的bean，是用mServerData字段进行json解析得到的。可能为null
     */
    private Object mServerObject;

    /**
     * 自定义监听器时使用。服务端返回的数据解析，可能有多个bean，是用mServerData字段进行json解析得到的。可能为null
     */
    private Map<String, Object> mServerObjectMap;

    public NetRetBean() {

    }

    public boolean isSuccess() {
        return mCallbackCode == CallbackCode.CODE_SUCCESS_REQUEST;
    }

    public CallbackCode getCallbackCode() {
        return mCallbackCode;
    }

    public void setCallbackCode(CallbackCode callbackCode) {
        this.mCallbackCode = callbackCode;
    }

    public Exception getException() {
        return mException;
    }

    public void setException(Exception exception) {
        this.mException = exception;
    }

    public String getServerCode() {
        return mServerCode;
    }

    public void setServerCode(String serverCode) {
        this.mServerCode = serverCode;
    }

    public String getServerMsg() {
        return mServerMsg;
    }

    public void setServerMsg(String serverMsg) {
        this.mServerMsg = serverMsg;
    }

    public String getServerTime() {
        return mServerTime;
    }

    public void setServerTime(String serverTime) {
        this.mServerTime = serverTime;
    }

    public String getServerData() {
        return mServerData;
    }

    public void setServerData(String serverData) {
        this.mServerData = serverData;
    }

    public Object getServerObject() {
        return mServerObject;
    }

    public void setServerObject(Object object) {
        this.mServerObject = object;
    }

    public Map<String, Object> getServerObjectMap() {
        return mServerObjectMap;
    }

    public void setServerObjectMap(Map<String, Object> serverObjectMap) {
        this.mServerObjectMap = serverObjectMap;
    }

    @Override
    public String toString() {
        return "NetRetBean{" +
                "mCallbackCode=" + mCallbackCode +
                ", mException=" + mException +
                ", mServerCode='" + mServerCode + '\'' +
                ", mServerMsg='" + mServerMsg + '\'' +
                ", mServerTime='" + mServerTime + '\'' +
                ", mServerData='" + mServerData + '\'' +
                ", mServerObject=" + mServerObject +
                ", mServerObjectMap=" + mServerObjectMap +
                '}';
    }
}
