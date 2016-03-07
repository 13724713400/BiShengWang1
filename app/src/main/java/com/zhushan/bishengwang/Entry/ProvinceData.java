package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/25.
 */
public class ProvinceData {
    private String region_id;
    private String region_name;

    @Override
    public String toString() {
        return "ProvinceData{" +
                "region_id='" + region_id + '\'' +
                ", region_name='" + region_name + '\'' +
                '}';
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }
}
