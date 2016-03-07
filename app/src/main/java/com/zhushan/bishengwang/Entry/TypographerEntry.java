package com.zhushan.bishengwang.Entry;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/13.
 */
public class TypographerEntry implements Serializable{
    private String filter1;
    private String lat;
    private String lng;
    private String filter3;
    private boolean isCheck;
    private boolean isPress;
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {

        return "TypographerEntry{" +
                "filter1='" + filter1 + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", filter3='" + filter3 + '\'' +
                ", isCheck=" + isCheck +
                ", isPress=" + isPress +
                '}';
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public boolean isPress() {
        return isPress;
    }

    public void setIsPress(boolean isPress) {
        this.isPress = isPress;
    }

    public String getFilter1() {
        return filter1;
    }

    public void setFilter1(String filter1) {
        this.filter1 = filter1;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getFilter3() {
        return filter3;
    }

    public void setFilter3(String filter3) {
        this.filter3 = filter3;
    }
}
