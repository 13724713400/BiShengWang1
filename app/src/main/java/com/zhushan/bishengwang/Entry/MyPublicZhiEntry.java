package com.zhushan.bishengwang.Entry;

import java.util.List;

/**
 * Created by Administrator on 2016/1/6.
 */
public class MyPublicZhiEntry {
    private int code;
    private List<MyPublicData> data;
    private String message;

    @Override
    public String toString() {
        return "MyPublicEntry{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<MyPublicData> getData() {
        return data;
    }

    public void setData(List<MyPublicData> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
