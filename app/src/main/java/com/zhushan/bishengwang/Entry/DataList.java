package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/4.
 */
public class DataList {

    private String company_area;
    private String company_name;
    private String company_telephone;
    private String id;
    private String img_thumb;
    private String lat;
    private String lng;
    private String mark;
    private String user_id;
    private String point;
    private String notice_number;
    private boolean isCheck;
    private boolean isCall;

    @Override
    public String toString() {
        return "DataList{" +
                "company_area='" + company_area + '\'' +
                ", company_name='" + company_name + '\'' +
                ", company_telephone='" + company_telephone + '\'' +
                ", id='" + id + '\'' +
                ", img_thumb='" + img_thumb + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", mark='" + mark + '\'' +
                ", user_id='" + user_id + '\'' +
                ", point='" + point + '\'' +
                ", notice_number='" + notice_number + '\'' +
                ", isCheck=" + isCheck +
                ", isCall=" + isCall +
                '}';
    }

    public boolean isCall() {
        return isCall;
    }

    public void setIsCall(boolean isCall) {
        this.isCall = isCall;
    }

    public String getNotice_number() {
        return notice_number;
    }

    public void setNotice_number(String notice_number) {
        this.notice_number = notice_number;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCompany_area() {
        return company_area;
    }

    public void setCompany_area(String company_area) {
        this.company_area = company_area;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_telephone() {
        return company_telephone;
    }

    public void setCompany_telephone(String company_telephone) {
        this.company_telephone = company_telephone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg_thumb() {
        return img_thumb;
    }

    public void setImg_thumb(String img_thumb) {
        this.img_thumb = img_thumb;
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

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
