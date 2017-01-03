package com.chenjian.net.demo.listener.sync;

import com.chenjian.net.bean.NetBaseBean;
import com.chenjian.net.bean.NetRetBean;
import com.chenjian.net.listener.sync.SyncNetHandleListener;
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

abstract public class SyncNetCustomBeanListener<Page extends NetBaseBean, T extends NetBaseBean> extends SyncNetHandleListener {

    @Override
    protected NetRetBean onReceivedRet(NetRetBean netRetBean) throws JSONException {
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
        return handleResult(netRetBean);
    }
}
