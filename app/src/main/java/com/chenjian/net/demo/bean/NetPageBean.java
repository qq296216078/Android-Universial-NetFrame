package com.chenjian.net.demo.bean;

import com.chenjian.net.bean.NetBaseBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者： ChenJian
 * 时间： 2016.12.16 13:52
 */

public class NetPageBean extends NetBaseBean {
    private String page;
    private String pageSize;
    private String total;

    @Override
    public void initByJson(JSONObject jsonObject) throws JSONException {
        this.page = jsonObject.optString("page");
        this.pageSize = jsonObject.optString("page_size");
        this.total = jsonObject.optString("total");
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "NetPageBean{" +
                "page='" + page + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
