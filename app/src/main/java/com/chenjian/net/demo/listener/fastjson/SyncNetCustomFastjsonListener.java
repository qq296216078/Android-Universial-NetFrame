package com.chenjian.net.demo.listener.fastjson;

import com.chenjian.net.bean.NetBaseBean;
import com.chenjian.net.bean.NetRetBean;
import com.chenjian.net.listener.sync.SyncNetHandleListener;
import com.chenjian.net.util.NetFastjsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 本类中对Page和 T 都使用fastjson解析，所以两个bean都有符合fastjson规则
 * 如果传入的Page和 T 不符合fastjson规则的话，将不能解析成功
 * <p>
 * 作者： ChenJian
 * 时间： 2016.12.16 13:51
 */

abstract public class SyncNetCustomFastjsonListener<Page extends NetBaseBean, T extends NetBaseBean> extends SyncNetHandleListener {

    @Override
    protected NetRetBean onReceivedRet(NetRetBean netRetBean) throws JSONException {
        String jsonString = netRetBean.getServerData();
        JSONObject jsonObject = new JSONObject(jsonString);

        Page page = NetFastjsonUtil.parseItemByFastjson(getClass(), 0, jsonString);

        List<T> list = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        for (int i = 0; i < jsonArray.length(); i++) {
            String string = jsonArray.getString(i);
            T t = NetFastjsonUtil.parseItemByFastjson(getClass(), 1, string);
            list.add(t);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("list", list);

        netRetBean.setServerObjectMap(map);
        return handleResult(netRetBean);
    }
}
