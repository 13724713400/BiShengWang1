package com.zhushan.bishengwang.Entry;

import java.util.List;

/**
 * Created by Administrator on 2016/1/16.
 */
public class privateEntry {

    private int code;
    private List<PrivateData> data;
    private String message;

    @Override
    public String toString() {
        return "privateEntry{" +
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

    public List<PrivateData> getData() {
        return data;
    }

    public void setData(List<PrivateData> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
