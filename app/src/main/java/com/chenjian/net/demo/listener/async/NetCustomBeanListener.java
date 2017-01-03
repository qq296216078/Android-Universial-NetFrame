package com.chenjian.net.demo.listener.async;

import com.chenjian.net.bean.NetBaseBean;
import com.chenjian.net.bean.NetRetBean;
import com.chenjian.net.listener.async.NetHandleListener;
import com.chenjian.net.listener.common.CallbackCode;
import com.chenjian.net.util.NetBaseBeanUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者： ChenJian
 * 时间： 2016.12.16 13:51
 */

abstract public class NetCustomBeanListener<Page extends NetBaseBean, T extends NetBaseBean> extends NetHandleListener {

    @Override
    protected void onReceivedRet(NetRetBean netRetBean) throws JSONException {
        JSONObject jsonObject = new JSONObject(netRetBean.getServerData());

        Page page = NetBaseBeanUtil.parseItem(getClass(), 0, jsonObject);

        List<T> list = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            T t = NetBaseBeanUtil.parseItem(getClass(), 1, object);
            list.add(t);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("list", list);

        netRetBean.setServerObjectMap(map);
        handleResult(netRetBean);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onSuccess(CallbackCode successCode, NetRetBean netRetBean) {
        Map<String, Object> map = netRetBean.getServerObjectMap();
        onSuccess((Page) map.get("page"), (List<T>) map.get("list"));
    }

    /**
     * 运行在ui线程，返回多个实体
     *
     * @param ts 当前bean的List
     */
    abstract protected void onSuccess(Page page, List<T> ts);
}
