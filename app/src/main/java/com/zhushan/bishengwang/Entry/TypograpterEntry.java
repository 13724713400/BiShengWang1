package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/4.
 */
public class TypograpterEntry {

    private int code;
    private TypographerData data;
    private String message;
    @Override
    public String toString() {
        return "TypograpterEntry{" +
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TypographerData getData() {
        return data;
    }

    public void setData(TypographerData data) {
        this.data = data;
    }
}
