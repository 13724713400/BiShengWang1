package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/25.
 */
public class CityeData {
    private String region_id;
    private String city;

    @Override
    public String toString() {
        return "CityeData{" +
                "region_id='" + region_id + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
