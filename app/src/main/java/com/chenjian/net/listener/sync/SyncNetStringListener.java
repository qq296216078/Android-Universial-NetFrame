package com.chenjian.net.listener.sync;

import com.chenjian.net.bean.NetRetBean;

import org.json.JSONException;

/**
 * 返回字String的网络请求Listener
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.15 14:54
 */

public class SyncNetStringListener extends SyncNetHandleListener {

    @Override
    protected NetRetBean onReceivedRet(NetRetBean netRetBean) throws JSONException {
        netRetBean.setServerObject(netRetBean.getServerData());
        return handleResult(netRetBean);
    }
}
