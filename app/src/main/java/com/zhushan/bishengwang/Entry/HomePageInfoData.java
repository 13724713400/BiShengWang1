package com.zhushan.bishengwang.Entry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/30.
 */
public class HomePageInfoData {

    private String addtime;
    private String age;
    private String area;
    private String cate_id;
    private String content;
    private String foucsCount;
    private String head_thumb;
    private String hit;
    private String id;
    private ArrayList<Img> img;
    private String lat;

    public ArrayList<Img> getImg() {
        return img;
    }

    public void setImg(ArrayList<Img> img) {
        this.img = img;
    }

    private String lng;
    private String messageCount;
    private String nickname;
    private String sex;
    private int status;
    private String telephone;
    private String title;
    private String user_id;
    private int user_type;
    private boolean isCall;
    private boolean isFocus;

    @Override
    public String toString() {
        return "HomePageInfoData{" +
                "addtime='" + addtime + '\'' +
                ", age='" + age + '\'' +
                ", area='" + area + '\'' +
                ", cate_id='" + cate_id + '\'' +
                ", content='" + content + '\'' +
                ", foucsCount='" + foucsCount + '\'' +
                ", head_thumb='" + head_thumb + '\'' +
                ", hit='" + hit + '\'' +
                ", id='" + id + '\'' +
                ", img=" + img +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", messageCount='" + messageCount + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", status=" + status +
                ", telephone='" + telephone + '\'' +
                ", title='" + title + '\'' +
                ", user_id='" + user_id + '\'' +
                ", user_type=" + user_type +
                ", isCall=" + isCall +
                ", isFocus=" + isFocus +
                '}';
    }

    public boolean isFocus() {
        return isFocus;
    }

    public void setIsFocus(boolean isFocus) {
        this.isFocus = isFocus;
    }

    public void setIsCall(boolean isCall) {
        this.isCall = isCall;
    }

    public boolean isCall() {
        return isCall;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFoucsCount() {
        return foucsCount;
    }

    public void setFoucsCount(String foucsCount) {
        this.foucsCount = foucsCount;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(String messageCount) {
        this.messageCount = messageCount;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getHead_thumb() {
        return head_thumb;
    }

    public void setHead_thumb(String head_thumb) {
        this.head_thumb = head_thumb;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
