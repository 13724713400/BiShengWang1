package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/8.
 */
public class AuthEntry {

    private int code;
    private AuthData data;
    private String message;


    @Override
    public String toString() {
        return "AuthEntry{" +
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

    public AuthData getData() {
        return data;
    }

    public void setData(AuthData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
