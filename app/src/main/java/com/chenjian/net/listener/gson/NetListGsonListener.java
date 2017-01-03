package com.chenjian.net.listener.gson;

import com.chenjian.net.bean.NetRetBean;
import com.chenjian.net.listener.async.NetHandleListener;
import com.chenjian.net.listener.common.CallbackCode;
import com.chenjian.net.util.NetGsonUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * 返回是List<Bean>的网络请求Listener
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.15 14:54
 */

abstract public class NetListGsonListener<T> extends NetHandleListener {

    @Override
    protected void onReceivedRet(NetRetBean netRetBean) throws JSONException {
        JSONArray array = new JSONArray(netRetBean.getServerData());
        List<T> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            String JsonString = array.getString(i);
            T t = NetGsonUtil.parseItemByGson(getClass(), 0, JsonString);
            list.add(t);
        }
        netRetBean.setServerObject(list);
        handleResult(netRetBean);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onSuccess(CallbackCode successCode, NetRetBean netRetBean) {
        onSuccess((List<T>) netRetBean.getServerObject());
    }

    /**
     * 运行在ui线程，返回多个实体
     *
     * @param ts 当前bean的List
     */
    abstract protected void onSuccess(List<T> ts);
}
