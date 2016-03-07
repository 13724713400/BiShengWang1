package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2015/12/28.
 */
public class LoginEntry {

    private int code;
    private String message;
    private LoginData data;

    @Override
    public String toString() {
        return "LoginEntry{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
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

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }
}
