package com.zhushan.bishengwang.Entry;

import java.util.List;

/**
 * Created by Administrator on 2016/1/9.
 */
public class PringTingPeapleData {
    private String area;
    private String birthday;
    private String company_area;
    private String company_name;
    private String description;
    private List<GalleryData> gallery;
    private String head_thumb;
    private String id;
    private String lat;
    private String lng;
    private String mark;
    private String nickname;
    private String point;
    private String rule;
    private String sex;
    private String user_id;
    private String notice_number;
    private String telephone;

    @Override
    public String toString() {
        return "PringTingPeapleData{" +
                "area='" + area + '\'' +
                ", birthday='" + birthday + '\'' +
                ", company_area='" + company_area + '\'' +
                ", company_name='" + company_name + '\'' +
                ", description='" + description + '\'' +
                ", gallery=" + gallery +
                ", head_thumb='" + head_thumb + '\'' +
                ", id='" + id + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", mark='" + mark + '\'' +
                ", nickname='" + nickname + '\'' +
                ", point='" + point + '\'' +
                ", rule='" + rule + '\'' +
                ", sex='" + sex + '\'' +
                ", user_id='" + user_id + '\'' +
                ", notice_number='" + notice_number + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNotice_number() {
        return notice_number;
    }

    public void setNotice_number(String notice_number) {
        this.notice_number = notice_number;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<GalleryData> getGallery() {
        return gallery;
    }

    public void setGallery(List<GalleryData> gallery) {
        this.gallery = gallery;
    }

    public String getHead_thumb() {
        return head_thumb;
    }

    public void setHead_thumb(String head_thumb) {
        this.head_thumb = head_thumb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
