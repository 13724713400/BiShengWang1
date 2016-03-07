package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2015/12/31.
 */
public class UserOutEntry {

    private int code;
    private String message;

    @Override
    public String toString() {
        return "UserOutEntry{" +
                "code=" + code +
                ", message='" + message + '\'' +
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
}
