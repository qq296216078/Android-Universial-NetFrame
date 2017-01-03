package com.chenjian.net.listener.fastjson;

import com.chenjian.net.bean.NetRetBean;
import com.chenjian.net.listener.sync.SyncNetHandleListener;
import com.chenjian.net.util.NetFastjsonUtil;

import org.json.JSONException;

/**
 * 返回是单个Bean的网络请求Listener
 * <p>
 * 由于去参数化(擦拭法)，也只有在
 * 超类（调用 getGenericSuperclass 方法）
 * 或者成员变量（调用 getGenericType 方法）
 * 或者方法（调用 getGenericParameterTypes 方法）
 * 像这些有方法返回 ParameterizedType 类型的时候才能反射成功
 * <p>
 * 所以使用的时候，必须使用他的子类，而不能直接使用本类
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.15 14:54
 */

abstract public class SyncNetSingleFastjsonListener<T> extends SyncNetHandleListener {

    @Override
    protected NetRetBean onReceivedRet(NetRetBean netRetBean) throws JSONException {
        String jsonString = netRetBean.getServerData();
        T t = NetFastjsonUtil.parseItemByFastjson(getClass(), 0, jsonString);
        netRetBean.setServerObject(t);
        return handleResult(netRetBean);
    }
}
