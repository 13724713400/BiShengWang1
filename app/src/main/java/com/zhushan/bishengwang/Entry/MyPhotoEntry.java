package com.zhushan.bishengwang.Entry;

import java.util.List;

/**
 * Created by Administrator on 2016/1/12.
 */
public class MyPhotoEntry {

    private int code;
    private List<MyPhotoData> data;
    private String message;

    @Override
    public String toString() {
        return "MyPhotoEntry{" +
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

    public List<MyPhotoData> getData() {
        return data;
    }

    public void setData(List<MyPhotoData> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
