package com.zhushan.bishengwang.Entry;

import java.util.List;

/**
 * Created by Administrator on 2016/1/25.
 */
public class CityEntry {
    private int code;
    private List<CityeData> data;
    private String message;

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ProvinceEntry{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<CityeData> getData() {
        return data;
    }

    public void setData(List<CityeData> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
