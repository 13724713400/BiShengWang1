package com.zhushan.bishengwang.Entry;

import java.util.List;

/**
 * Created by Administrator on 2016/1/4.
 */
public class TypographerData {
   private Banner banner;
   private List<DataList> dataList;

    @Override
    public String toString() {
        return "TypographerData{" +
                "banner=" + banner +
                ", dataList=" + dataList +
                '}';
    }

    public List<DataList> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataList> dataList) {
        this.dataList = dataList;
    }

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }
}
