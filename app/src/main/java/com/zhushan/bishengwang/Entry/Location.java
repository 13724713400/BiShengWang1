package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2015/12/17.
 */
public class Location {

    private double lat;
    private double lng;

    @Override
    public String toString() {
        return "Location{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
