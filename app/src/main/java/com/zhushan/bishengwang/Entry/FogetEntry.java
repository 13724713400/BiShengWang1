package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/19.
 */
public class FogetEntry {

    private int code;
    private String message;
    private String data;

    @Override
    public String toString() {
        return "FogetEntry{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
