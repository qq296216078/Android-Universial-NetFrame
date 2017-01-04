package com.chenjian.net.demo.bean;

import com.chenjian.net.bean.NetCommonBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： ChenJian
 * 时间： 2016.12.30 20:40
 */

public class NetNewsBean extends NetCommonBean {
    private String title;
    private String desc;
    private String date;

    @Override
    public NetNewsBean getBeanByJson(JSONObject jsonObject) throws JSONException {
        // 你要自己解析：
//        this.title = jsonObject.optString("title");
//        this.desc = jsonObject.optString("desc");
//        this.date = jsonObject.optString("date");
//        return this;

        // 你要用Gson解析，只需要一行代码：
//        return new Gson().fromJson(jsonObject.toString(), NetNewsBean.class);

        // 你要用fastjson解析，只需要一行代码
//        return JSON.parseObject(jsonObject.toString(), NetNewsBean.class);

        return null;
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
        return "NetNewsBean{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

}
