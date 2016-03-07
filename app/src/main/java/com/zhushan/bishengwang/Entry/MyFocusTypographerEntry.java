package com.zhushan.bishengwang.Entry;

import java.util.List;

/**
 * Created by Administrator on 2016/1/15.
 */
public class MyFocusTypographerEntry {

    private int code;
    private List<MyFocusTypographerData> data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<MyFocusTypographerData> getData() {
        return data;
    }

    public void setData(List<MyFocusTypographerData> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MyFocusTypographerEntry{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
