package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2015/12/31.
 */
public class UserInfoEntry {

    private int code;
    private String message;
    private UserInfoData data;

    @Override
    public String toString() {
        return "UserInfoEntry{" +
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

    public UserInfoData getData() {
        return data;
    }

    public void setData(UserInfoData data) {
        this.data = data;
    }
}
