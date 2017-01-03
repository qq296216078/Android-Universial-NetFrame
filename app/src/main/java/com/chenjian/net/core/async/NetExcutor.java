package com.chenjian.net.core.async;

import android.os.AsyncTask;

import com.chenjian.net.core.common.RequestType;
import com.chenjian.net.request.RequestUtil;
import com.chenjian.net.token.TokenUtil;

/**
 * 网络请求处理核心类
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.13 17:42
 */

public class NetExcutor {

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
    private NetListener mExcutorListener;

    public void setUrl(String url) {
        this.mUrl = url;
    }

    private void setRequestType(RequestType requestType) {
        this.mRequestType = requestType;
    }

    public void setParams(String params) {
        this.mParams = params;
    }

    public void setExcutorListener(NetListener listener) {
        this.mExcutorListener = listener;
    }

    public void setWaitForToken(boolean waitForToken) {
        isWaitForToken = waitForToken;
    }

    public void get() {
        setRequestType(RequestType.REQUEST_TYPE_GET);
        new NetTask().execute();
    }

    public void post() {
        setRequestType(RequestType.REQUEST_TYPE_POST);
        new NetTask().execute();
    }

    /**
     * 网络请求异步任务，android 8 以上系统会自己对异步任务做线程池处理
     * <p>
     * 此处也可以改成自己去写线程池，让调用者可以配置线程池的相关参数
     */
    private class NetTask extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            if (isWaitForToken) {
                TokenUtil.waitToken();
            }
            try {
                String result = request();
                mExcutorListener.sendSuccess(result);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                mExcutorListener.sendError(e);
                return false;
            }
        }
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
