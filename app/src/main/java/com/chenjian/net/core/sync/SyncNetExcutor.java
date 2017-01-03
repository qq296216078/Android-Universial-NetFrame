package com.chenjian.net.core.sync;

import com.chenjian.net.bean.NetRetBean;
import com.chenjian.net.core.common.RequestType;
import com.chenjian.net.request.RequestUtil;
import com.chenjian.net.token.TokenUtil;

/**
 * 网络请求处理核心类
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.13 17:42
 */

public class SyncNetExcutor {

    /**
     * 请求url
     */
    private String mUrl;

    /**
     * 请求类型
     */
    private RequestType mRequestType;

    /**
     * post请求时的参数
     */
    private String mParams;

    /**
     * 是否先等待token请求成功
     */
    private boolean isWaitForToken;

    /**
     * 请求后的回调
     */
    private SyncNetListener mSyncNetListener;

    public void setUrl(String url) {
        this.mUrl = url;
    }

    private void setRequestType(RequestType requestType) {
        this.mRequestType = requestType;
    }

    public void setParams(String params) {
        this.mParams = params;
    }

    public void setWaitForToken(boolean waitForToken) {
        isWaitForToken = waitForToken;
    }

    public void setSyncNetListener(SyncNetListener syncNetListener) {
        this.mSyncNetListener = syncNetListener;
    }

    public NetRetBean get() {
        setRequestType(RequestType.REQUEST_TYPE_GET);
        return startRequest();
    }

    public NetRetBean post() {
        setRequestType(RequestType.REQUEST_TYPE_POST);
        return startRequest();
    }

    /**
     * 同步请求，直接返回
     *
     * @return 返回 NetRetBean
     */
    private NetRetBean startRequest() {
        if (isWaitForToken) {
            TokenUtil.waitToken();
        }
        try {
            String result = request();
            return mSyncNetListener.sendSuccess(result);
        } catch (Exception e) {
            e.printStackTrace();
            return mSyncNetListener.sendError(e);
        }
    }

    public String getString() {
        setRequestType(RequestType.REQUEST_TYPE_GET);
        return startRequestString();
    }

    public String postString() {
        setRequestType(RequestType.REQUEST_TYPE_POST);
        return startRequestString();
    }

    /**
     * 同步请求，直接返回
     *
     * @return 返回 String
     */
    private String startRequestString() {
        try {
            return request();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String request() throws Exception {
        String result = null;
        switch (mRequestType) {
            case REQUEST_TYPE_GET:
                result = RequestUtil.getRequest(mUrl);
                break;

            case REQUEST_TYPE_POST:
                result = RequestUtil.postRequest(mUrl, mParams);
                break;

            default:
                break;
        }
        return result;
    }

}