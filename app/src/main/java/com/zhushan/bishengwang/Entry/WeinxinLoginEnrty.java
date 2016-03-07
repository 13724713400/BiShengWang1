package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/20.
 */
public class WeinxinLoginEnrty {
    private int code;
    private WeinxinLoginData data;
    private String message;

    @Override
    public String toString() {
        return "WeinxinLoginEnrty{" +
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

    public WeinxinLoginData getData() {
        return data;
    }

    public void setData(WeinxinLoginData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
