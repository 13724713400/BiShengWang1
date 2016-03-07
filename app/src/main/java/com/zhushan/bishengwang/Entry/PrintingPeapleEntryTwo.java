package com.zhushan.bishengwang.Entry;

import java.util.List;

/**
 * Created by Administrator on 2015/12/31.
 */
public class PrintingPeapleEntryTwo {

    private int code;
    private String message;
    private List<PrintingPeapleData> data;

    @Override
    public String toString() {
        return "PrintingPeapleEntry{" +
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

    public List<PrintingPeapleData> getData() {
        return data;
    }

    public void setData(List<PrintingPeapleData> data) {
        this.data = data;
    }
}
