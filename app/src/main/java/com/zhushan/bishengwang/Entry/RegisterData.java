package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2015/12/25.
 */
public class RegisterData {

    private int uid;

    @Override
    public String toString() {
        return "RegisterData{" +
                "uid=" + uid +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
