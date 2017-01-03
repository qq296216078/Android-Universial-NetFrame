package com.chenjian.net.demo.bean;

import com.chenjian.net.bean.NetBaseBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： ChenJian
 * 时间： 2016.12.16 13:57
 */

public class NetInfoBean extends NetBaseBean {
    private String title;
    private String desc;
    private String date;

    @Override
    public void initByJson(JSONObject jsonObject) throws JSONException {
        this.title = jsonObject.getString("title");
        this.desc = jsonObject.getString("desc");
        this.date = jsonObject.getString("date");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "NetInfoBean{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
