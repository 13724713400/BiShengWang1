package com.zhushan.bishengwang.Entry;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/31.
 */
public class ShuaiXuanEntry implements Serializable{

    private String sex;
    private String label;
    private String orderField;
    private int index;
    private boolean isPress;
    private String lat;
    private String lng;
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "ShuaiXuanEntry{" +
                "sex='" + sex + '\'' +
                ", label='" + label + '\'' +
                ", orderField='" + orderField + '\'' +
                ", index=" + index +
                ", isPress=" + isPress +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
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

    public boolean isPress() {
        return isPress;
    }

    public void setIsPress(boolean isPress) {
        this.isPress = isPress;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }
}
