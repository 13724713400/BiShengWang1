package com.zhushan.bishengwang.Entry;

import java.util.List;

/**
 * Created by Administrator on 2015/12/22.
 */
public class TypePepleDirectorSettingEntry {

    private int code;

    private String message;

    private List<DirectorSettingData> data;

    @Override
    public String toString() {
        return "DirectorSettingEntry{" +
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

    public List<DirectorSettingData> getData() {
        return data;
    }

    public void setData(List<DirectorSettingData> data) {
        this.data = data;
    }
}
