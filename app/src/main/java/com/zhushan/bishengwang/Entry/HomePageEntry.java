package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/13.
 */
public class HomePageEntry {

    private int cate_id;
    private String distance;
    private String lat;
    private String lng;
    private String filter1;
    private int  filter2;
    private String filter3;
    private boolean isCkeck;
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
        return "HomePageEntry{" +
                "cate_id=" + cate_id +
                ", distance='" + distance + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", filter1='" + filter1 + '\'' +
                ", filter2=" + filter2 +
                ", filter3='" + filter3 + '\'' +
                ", isCkeck=" + isCkeck +
                ", isPress=" + isPress +
                '}';
    }

    public boolean isPress() {
        return isPress;
    }

    public void setIsPress(boolean isPress) {
        this.isPress = isPress;
    }

    public boolean isCkeck() {
        return isCkeck;
    }

    public void setIsCkeck(boolean isCkeck) {
        this.isCkeck = isCkeck;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
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

    public String getFilter1() {
        return filter1;
    }

    public void setFilter1(String filter1) {
        this.filter1 = filter1;
    }

    public int getFilter2() {
        return filter2;
    }

    public void setFilter2(int filter2) {
        this.filter2 = filter2;
    }

    public String getFilter3() {
        return filter3;
    }

    public void setFilter3(String filter3) {
        this.filter3 = filter3;
    }

}
