package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2015/12/25.
 */
public class RegisterEntry {

    private String message;
    private int code;
    private RegisterData data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public RegisterData getData() {
        return data;
    }

    public void setData(RegisterData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RegisterEntry{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
