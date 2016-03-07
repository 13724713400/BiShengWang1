package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/9.
 */
public class PringTingPeapleEntry {

    private int code;
    private PringTingPeapleData data;
    private String message;

    @Override
    public String toString() {
        return "PringTingPeapleEntry{" +
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

    public PringTingPeapleData getData() {
        return data;
    }

    public void setData(PringTingPeapleData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
