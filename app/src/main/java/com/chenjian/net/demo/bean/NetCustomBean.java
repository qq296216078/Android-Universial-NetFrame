package com.chenjian.net.demo.bean;

import com.chenjian.net.bean.NetBaseBean;
import com.chenjian.net.util.NetBaseBeanUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： ChenJian
 * 时间： 2017.1.9 18:27
 */

public class NetCustomBean<T extends NetBaseBean> extends NetBaseBean {
    private NetPageBean mNetPageBean;
    private List<T> mTList;

    @Override
    public void initByJson(JSONObject jsonObject) throws JSONException {
        this.mNetPageBean = new NetPageBean();
        this.mNetPageBean.initByJson(jsonObject);

        this.mTList = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        for (int i = 0; i < jsonArray.length(); i++) {
            T t = NetBaseBeanUtil.parseItem(getClass(), 0, jsonArray.getJSONObject(i));
            mTList.add(t);
        }
    }

    public NetPageBean getNetPageBean() {
        return mNetPageBean;
    }

    public void setNetPageBean(NetPageBean netPageBean) {
        this.mNetPageBean = netPageBean;
    }

    public List<T> getTList() {
        return mTList;
    }

    public void setTList(List<T> tList) {
        this.mTList = tList;
    }
}
