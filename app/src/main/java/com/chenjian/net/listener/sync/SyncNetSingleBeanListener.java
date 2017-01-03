package com.chenjian.net.listener.sync;

import com.chenjian.net.bean.NetBaseBean;
import com.chenjian.net.bean.NetRetBean;
import com.chenjian.net.util.NetBaseBeanUtil;

import org.json.JSONException;
import org.json.JSONObject;

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

abstract public class SyncNetSingleBeanListener<T extends NetBaseBean> extends SyncNetHandleListener {

    @Override
    protected NetRetBean onReceivedRet(NetRetBean netRetBean) throws JSONException {
        JSONObject object = new JSONObject(netRetBean.getServerData());
        T t = NetBaseBeanUtil.parseItem(getClass(), 0, object);
        netRetBean.setServerObject(t);
        return handleResult(netRetBean);
    }
}
