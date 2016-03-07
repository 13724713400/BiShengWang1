package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2015/12/17.
 */
public class AddressChangeEntry {
    private ResultPosition result;
    private int status;
    @Override
    public String toString() {
        return "AddressChangeEntry{" +
                "result=" + result +
                ", status=" + status +
                '}';
    }

    public ResultPosition getResult() {
        return result;
    }

    public void setResult(ResultPosition result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
