package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/11.
 */
public class AuthCheckEntry {

    private String data;
    private String message;
    private int code;

    @Override
    public String toString() {
        return "AuthCheckEntry{" +
                "data='" + data + '\'' +
                ", message='" + message + '\'' +
                ", code=" + code +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
