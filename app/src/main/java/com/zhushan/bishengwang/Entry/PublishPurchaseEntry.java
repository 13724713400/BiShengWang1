package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2015/12/29.
 */
public class PublishPurchaseEntry {

    private int code;

    private String message;

    @Override
    public String toString() {
        return "PublishPurchaseEntry{" +
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
