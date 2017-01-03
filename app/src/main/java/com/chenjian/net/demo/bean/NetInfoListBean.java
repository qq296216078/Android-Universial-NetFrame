package com.chenjian.net.demo.bean;

import com.chenjian.net.bean.NetBaseBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： ChenJian
 * 时间： 2016.12.16 13:54
 */

public class NetInfoListBean extends NetBaseBean {

    private NetPageBean pageBean;
    private List<NetInfoBean> infoBeen;

    @Override
    public void initByJson(JSONObject jsonObject) throws JSONException {
        this.pageBean = new NetPageBean();
        this.pageBean.initByJson(jsonObject);

        JSONArray jsonArray = jsonObject.getJSONArray("list");
        this.infoBeen = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            NetInfoBean infoBean = new NetInfoBean();
            infoBean.initByJson(jsonArray.getJSONObject(i));
            infoBeen.add(infoBean);
        }
    }

    public NetPageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(NetPageBean pageBean) {
        this.pageBean = pageBean;
    }

    public List<NetInfoBean> getInfoBeen() {
        return infoBeen;
    }

    public void setInfoBeen(List<NetInfoBean> infoBeen) {
        this.infoBeen = infoBeen;
    }

    @Override
    public String toString() {
        return "NetInfoListBean{" +
                "pageBean=" + pageBean +
                ", infoBeen=" + infoBeen +
                '}';
    }
}
