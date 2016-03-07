package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/8.
 */
public class MessageEntry {
    private int code;
    private String data;
    private String message;

    @Override
    public String toString() {
        return "MessageEntry{" +
                "code=" + code +
                ", data='" + data + '\'' +
                ", message='" + message + '\'' +
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
