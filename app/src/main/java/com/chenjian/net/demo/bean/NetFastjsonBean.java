package com.chenjian.net.demo.bean;

/**
 * 作者： ChenJian
 * 时间： 2017.1.4 9:42
 */

public class NetFastjsonBean {
    private String title;
    private String desc;
    private String date;

    /**
     * fastjson一定要写get和set方法才行。他的规则请参考相关文档，这里做详细介绍
     */

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
        return "NetFastjsonBean{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
