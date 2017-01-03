package com.chenjian.net.listener.async;

import com.chenjian.net.bean.NetRetBean;
import com.chenjian.net.listener.common.CallbackCode;

import org.json.JSONException;

/**
 * 返回字String的网络请求Listener
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.15 14:54
 */

abstract public class NetStringListener extends NetHandleListener {

    @Override
    protected void onReceivedRet(NetRetBean netRetBean) throws JSONException {
        netRetBean.setServerObject(netRetBean.getServerData());
        handleResult(netRetBean);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onSuccess(CallbackCode successCode, NetRetBean netRetBean) {
        onSuccess((String) netRetBean.getServerObject());
    }

    /**
     * 运行在ui线程，返回单个实体
     *
     * @param string 当前string
     */
    abstract protected void onSuccess(String string);
}
