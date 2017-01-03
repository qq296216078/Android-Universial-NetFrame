package com.chenjian.net.demo.listener.gson;

import com.chenjian.net.bean.NetBaseBean;
import com.chenjian.net.bean.NetRetBean;
import com.chenjian.net.listener.async.NetHandleListener;
import com.chenjian.net.listener.common.CallbackCode;
import com.chenjian.net.util.NetGsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 本类中对Page和 T 都使用Gson解析，所以两个bean都有符合Gson规则
 * 如果传入的Page和 T 不符合Gson规则的话，将不能解析成功
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.16 13:51
 */

abstract public class NetCustomGsonListener<Page extends NetBaseBean, T extends NetBaseBean> extends NetHandleListener {

    @Override
    protected void onReceivedRet(NetRetBean netRetBean) throws JSONException {
        String jsonString = netRetBean.getServerData();
        // 使用Gson解析
        Page page = NetGsonUtil.parseItemByGson(getClass(), 0, jsonString);

        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        List<T> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            String string = jsonArray.getString(i);
            // 使用Gson解析
            T t = NetGsonUtil.parseItemByGson(getClass(), 1, string);
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
