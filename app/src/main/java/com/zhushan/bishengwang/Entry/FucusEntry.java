package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/15.
 */
public class FucusEntry {
    private int code;
    private String message;

    @Override
    public String toString() {
        return "FucusEntry{" +
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
