package com.zhushan.bishengwang.Entry;

import java.util.List;

/**
 * Created by Administrator on 2015/12/28.
 */
public class DirectorSettingData {

    private String  cate_name;

    private String id;

    private List<Label_list> label_list;

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Label_list> getLabel_list() {
        return label_list;
    }

    public void setLabel_list(List<Label_list> label_list) {
        this.label_list = label_list;
    }

    @Override
    public String toString() {
        return "DirectorSettingData{" +
                "cate_name='" + cate_name + '\'' +
                ", id='" + id + '\'' +
                ", label_list=" + label_list +
                '}';
    }
}
