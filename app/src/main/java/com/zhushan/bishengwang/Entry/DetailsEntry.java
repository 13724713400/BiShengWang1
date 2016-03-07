package com.zhushan.bishengwang.Entry;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/5.
 */
public class DetailsEntry implements Serializable{

    private int code;
    private  DetailsData data;
    private String message;

    @Override
    public String toString() {
        return "DetailsEntry{" +
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DetailsData getData() {
        return data;
    }

    public void setData(DetailsData data) {
        this.data = data;
    }
}
