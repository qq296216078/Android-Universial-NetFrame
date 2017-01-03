package com.chenjian.net.listener.gson;

import com.chenjian.net.bean.NetRetBean;
import com.chenjian.net.listener.async.NetHandleListener;
import com.chenjian.net.listener.common.CallbackCode;
import com.chenjian.net.util.NetGsonUtil;

import org.json.JSONException;

/**
 * 返回是单个Bean的网络请求Listener
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.15 14:54
 */

abstract public class NetSingleGsonListener<T> extends NetHandleListener {

    @Override
    protected void onReceivedRet(NetRetBean netRetBean) throws JSONException {
        String jsonString = netRetBean.getServerData();
        T t = NetGsonUtil.parseItemByGson(getClass(), 0, jsonString);
        netRetBean.setServerObject(t);
        handleResult(netRetBean);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onSuccess(CallbackCode successCode, NetRetBean netRetBean) {
        onSuccess((T) netRetBean.getServerObject());
    }

    /**
     * 运行在ui线程，返回单个实体
     *
     * @param t 当前bean
     */
    abstract protected void onSuccess(T t);
}
