package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2015/12/17.
 */
public class ResultPosition {

    private int confidence;
    private Location location;
    private int precise;

    @Override
    public String toString() {
        return "ResultPosition{" +
                "confidence=" + confidence +
                ", location=" + location +
                ", precise=" + precise +
                '}';
    }

    public int getConfidence() {
        return confidence;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getPrecise() {
        return precise;
    }

    public void setPrecise(int precise) {
        this.precise = precise;
    }
}
