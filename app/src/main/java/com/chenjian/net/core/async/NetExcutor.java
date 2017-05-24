package com.chenjian.net.core.async;

import android.os.AsyncTask;

import com.chenjian.net.core.common.RequestType;
import com.chenjian.net.request.HttpEngine;
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
     * 网络请求引擎
     */
    private HttpEngine mHttpEngine;

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
    private boolean mIsWaitForToken;

    /**
     * 请求后的回调
     */
    private NetListener mNetListener;

    public void setUrl(String url) {
        this.mUrl = url;
    }

    private void setRequestType(RequestType requestType) {
        this.mRequestType = requestType;
    }

    public void setParams(String params) {
        this.mParams = params;
    }

    public void setNetListener(NetListener listener) {
        this.mNetListener = listener;
    }

    public void setIsWaitForToken(boolean isWaitForToken) {
        this.mIsWaitForToken = isWaitForToken;
    }

    public void setHttpEngine(HttpEngine httpEngine) {
        this.mHttpEngine = httpEngine;
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
            if (mIsWaitForToken) {
                TokenUtil.waitToken();
            }
            try {
                String result = request();
                mNetListener.sendSuccess(result);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                mNetListener.sendError(e);
                return false;
            }
        }
    }

    private String request() throws Exception {
        String result = null;
        switch (mRequestType) {
            case REQUEST_TYPE_GET:
                result = RequestUtil.getRequest(mHttpEngine, mUrl);
                break;

            case REQUEST_TYPE_POST:
                result = RequestUtil.postRequest(mHttpEngine, mUrl, mParams);
                break;

            default:
                break;
        }
        return result;
    }

}
