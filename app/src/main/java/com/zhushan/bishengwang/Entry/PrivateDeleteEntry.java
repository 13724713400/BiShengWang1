package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/19.
 */
public class PrivateDeleteEntry {
    private String data;
    private int code;
    private String message;

    @Override
    public String toString() {
        return "PrivateDeleteEntry{" +
                "data='" + data + '\'' +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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
}
