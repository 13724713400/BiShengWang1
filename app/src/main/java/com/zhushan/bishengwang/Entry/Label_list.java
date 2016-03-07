package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2015/12/28.
 */
public class Label_list {

    private String label_id;
    private String label_name;
    private boolean ischeck;

    public boolean ischeck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public String getLabel_id() {
        return label_id;
    }

    public void setLabel_id(String label_id) {
        this.label_id = label_id;
    }

    public String getLabel_name() {
        return label_name;
    }

    @Override
    public String toString() {
        return "Label_list{" +
                "label_id='" + label_id + '\'' +
                ", label_name='" + label_name + '\'' +
                '}';
    }

    public void setLabel_name(String label_name) {
        this.label_name = label_name;
    }
}
