package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2015/12/25.
 */
public class UploadHeaderEntry {

    private int code;

    private UploadHeaderData data;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UploadHeaderData getData() {
        return data;
    }

    public void setData(UploadHeaderData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UploadHeaderEntry{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
