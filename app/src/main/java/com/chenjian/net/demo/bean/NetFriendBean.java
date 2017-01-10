package com.chenjian.net.demo.bean;

import com.chenjian.net.bean.NetBaseBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： ChenJian
 * 时间： 2017.1.9 18:27
 */

public class NetFriendBean extends NetBaseBean {
    private NetPageBean mNetPageBean;
    private List<NetUserBean> mNetUserBeen;

    @Override
    public void initByJson(JSONObject jsonObject) throws JSONException {
        this.mNetPageBean = new NetPageBean();
        this.mNetPageBean.initByJson(jsonObject);

        this.mNetUserBeen = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        for (int i = 0; i < jsonArray.length(); i++) {
            NetUserBean netUserBean = new NetUserBean();
            netUserBean.initByJson(jsonArray.getJSONObject(i));
            mNetUserBeen.add(netUserBean);
        }
    }
}
