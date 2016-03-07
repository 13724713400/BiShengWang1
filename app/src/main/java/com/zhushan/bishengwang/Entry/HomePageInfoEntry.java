package com.zhushan.bishengwang.Entry;

import java.util.List;

/**
 * Created by Administrator on 2015/12/30.
 */
public class HomePageInfoEntry {

    private int code;
    private String message;
    private List<HomePageInfoData> data;

    @Override
    public String toString() {
        return "HomePageInfoEntry{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<HomePageInfoData> getData() {
        return data;
    }

    public void setData(List<HomePageInfoData> data) {
        this.data = data;
    }
}
