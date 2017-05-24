package com.chenjian.net.helper;

import com.chenjian.net.bean.NetRetBean;
import com.chenjian.net.core.async.NetExcutor;
import com.chenjian.net.core.async.NetListener;
import com.chenjian.net.core.common.RequestType;
import com.chenjian.net.core.sync.SyncNetExcutor;
import com.chenjian.net.core.sync.SyncNetListener;
import com.chenjian.net.data.NetConstants;
import com.chenjian.net.request.HttpEngine;
import com.chenjian.net.request.HttpUtil;

/**
 * 网络请求工具类
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.14 11:24
 */

public class NetHelper2 {
    private HttpEngine mHttpEngine;
    private String mUrl;
    private String mParam;
    private boolean mIsWaitForToken;
    private NetListener mNetListener;
    private SyncNetListener mSyncNetListener;

    private NetHelper2() {
        this.mIsWaitForToken = NetConstants.defaultWaitForToken;
    }

    public static NetHelper2 create() {
        return new NetHelper2();
    }

    /**
     * 这里可以指定默认用哪个网络请求引擎。
     * 当前默认用的是系统自带的HttpURLConnection
     * 开发者也可以根据自己的需要，修改成okhttp、retrofit
     *
     * @param httpEngine 网络请求引擎
     * @return this
     */
    public NetHelper2 httpEngine(HttpEngine httpEngine) {
        this.mHttpEngine = httpEngine;
        return this;
    }

    public NetHelper2 url(String url) {
        this.mUrl = url;
        return this;
    }

    public NetHelper2 param(String param) {
        this.mParam = param;
        return this;
    }

    public NetHelper2 isWaitForToken(boolean isWaitForToken) {
        this.mIsWaitForToken = isWaitForToken;
        return this;
    }

    public NetHelper2 netListener(NetListener netListener) {
        this.mNetListener = netListener;
        return this;
    }

    public NetHelper2 syncNetListener(SyncNetListener syncNetListener) {
        this.mSyncNetListener = syncNetListener;
        return this;
    }

    public void get() {
        request(RequestType.REQUEST_TYPE_GET);
    }

    public void post() {
        request(RequestType.REQUEST_TYPE_POST);
    }

    private void request(RequestType requestType) {
        NetExcutor netExcutor = new NetExcutor();

        if (mHttpEngine == null) {
            mHttpEngine = new HttpUtil();
        }
        netExcutor.setHttpEngine(mHttpEngine);

        if (mUrl == null || mUrl.equals("")) {
            return;
        }
        netExcutor.setUrl(mUrl);

        netExcutor.setParams(mParam);

        netExcutor.setIsWaitForToken(mIsWaitForToken);

        netExcutor.setNetListener(mNetListener);

        switch (requestType) {
            case REQUEST_TYPE_GET:
                netExcutor.get();
                break;
            case REQUEST_TYPE_POST:
                netExcutor.post();
                break;
        }
    }

    public NetRetBean syncGet() {
        return syncRequest(RequestType.REQUEST_TYPE_GET);
    }

    public NetRetBean syncPost() {
        return syncRequest(RequestType.REQUEST_TYPE_POST);
    }

    private NetRetBean syncRequest(RequestType requestType) {
        NetRetBean netRetBean = null;
        SyncNetExcutor syncNetExcutor = new SyncNetExcutor();

        if (mHttpEngine == null) {
            mHttpEngine = new HttpUtil();
        }
        syncNetExcutor.setHttpEngine(mHttpEngine);

        if (mUrl == null || mUrl.equals("")) {
            return null;
        }
        syncNetExcutor.setUrl(mUrl);

        syncNetExcutor.setParams(mParam);

        syncNetExcutor.setIsWaitForToken(mIsWaitForToken);

        syncNetExcutor.setSyncNetListener(mSyncNetListener);

        switch (requestType) {
            case REQUEST_TYPE_GET:
                netRetBean = syncNetExcutor.get();
                break;
            case REQUEST_TYPE_POST:
                netRetBean = syncNetExcutor.post();
                break;
        }

        return netRetBean;
    }
}
