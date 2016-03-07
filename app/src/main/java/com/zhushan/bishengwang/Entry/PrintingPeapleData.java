package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2015/12/31.
 */
public class PrintingPeapleData {

    private String age;
    private String area;
    private String company_name;
    private String description;
    private String foucsCount;
    private String head_thumb;
    private String id;
    private String lat;
    private String lng;
    private String nickname;
    private String point;
    private String rule;
    private String sex;
    private String user_id;
    private boolean isFocus;
    @Override
    public String toString() {
        return "PrintingPeapleData{" +
                "age='" + age + '\'' +
                ", area='" + area + '\'' +
                ", company_name='" + company_name + '\'' +
                ", description='" + description + '\'' +
                ", foucsCount='" + foucsCount + '\'' +
                ", head_thumb='" + head_thumb + '\'' +
                ", id='" + id + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", nickname='" + nickname + '\'' +
                ", point='" + point + '\'' +
                ", rule='" + rule + '\'' +
                ", sex='" + sex + '\'' +
                ", user_id='" + user_id + '\'' +
                ", isFocus=" + isFocus +
                '}';
    }

    public boolean isFocus() {
        return isFocus;
    }

    public void setIsFocus(boolean isFocus) {
        this.isFocus = isFocus;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getFoucsCount() {
        return foucsCount;
    }

    public void setFoucsCount(String foucsCount) {
        this.foucsCount = foucsCount;
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
