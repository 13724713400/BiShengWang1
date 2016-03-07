package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/5.
 */
public class ItemData {
    private Lt1 lt1;
    private String lt2;
    private Lt3 lt3;

    @Override
    public String toString() {
        return "ItemData{" +
                "lt1=" + lt1 +
                ", lt2=" + lt2 +
                ", lt3=" + lt3 +
                '}';
    }

    public Lt1 getLt1() {
        return lt1;
    }

    public void setLt1(Lt1 lt1) {
        this.lt1 = lt1;
    }

    public String getLt2() {
        return lt2;
    }

    public void setLt2(String lt2) {
        this.lt2 = lt2;
    }

    public Lt3 getLt3() {
        return lt3;
    }

    public void setLt3(Lt3 lt3) {
        this.lt3 = lt3;
    }
}
