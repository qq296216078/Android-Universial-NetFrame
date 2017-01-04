package com.chenjian.net.demo.bean;

import com.chenjian.net.bean.NetBaseBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： ChenJian
 * 时间： 2016.12.16 13:57
 */

public class NetInfoBean extends NetBaseBean {
    private String infoTitle;
    private String infoDesc;
    private String infoDate;

    @Override
    public void initByJson(JSONObject jsonObject) throws JSONException {
        this.infoTitle = jsonObject.getString("title");
        this.infoDesc = jsonObject.getString("desc");
        this.infoDate = jsonObject.getString("date");
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public String getInfoDesc() {
        return infoDesc;
    }

    public void setInfoDesc(String infoDesc) {
        this.infoDesc = infoDesc;
    }

    public String getInfoDate() {
        return infoDate;
    }

    public void setInfoDate(String infoDate) {
        this.infoDate = infoDate;
    }

    @Override
    public String toString() {
        return "NetInfoBean{" +
                "infoTitle='" + infoTitle + '\'' +
                ", infoDesc='" + infoDesc + '\'' +
                ", infoDate='" + infoDate + '\'' +
                '}';
    }
}
