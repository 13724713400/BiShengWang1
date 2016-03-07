package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2015/12/31.
 */
public class UserAuthEntry {

    private int code;
    private UserAuthData data;
    private String message;

    @Override
    public String toString() {
        return "UserAuthEntry{" +
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

    public UserAuthData getData() {
        return data;
    }

    public void setData(UserAuthData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
