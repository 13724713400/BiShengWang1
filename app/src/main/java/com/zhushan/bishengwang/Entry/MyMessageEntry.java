package com.zhushan.bishengwang.Entry;

import java.util.List;

/**
 * Created by Administrator on 2016/1/8.
 */
public class MyMessageEntry {

    private int code;
    private List<MyMessageData> data;
    private String message;

    @Override
    public String toString() {
        return "MyMessageEntry{" +
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

    public List<MyMessageData> getData() {
        return data;
    }

    public void setData(List<MyMessageData> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
