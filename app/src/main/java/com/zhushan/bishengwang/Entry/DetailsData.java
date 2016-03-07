package com.zhushan.bishengwang.Entry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/5.
 */
public class DetailsData implements Serializable{

    private String addtime;
    private String cate_id;
    private List<CommentData> comment;
    private String content;
    private String endtime;
    private String hit;
    private String foucsCount;
    private String area;

    @Override
    public String toString() {
        return "DetailsData{" +
                "addtime='" + addtime + '\'' +
                ", cate_id='" + cate_id + '\'' +
                ", comment=" + comment +
                ", content='" + content + '\'' +
                ", endtime='" + endtime + '\'' +
                ", hit='" + hit + '\'' +
                ", foucsCount='" + foucsCount + '\'' +
                ", area='" + area + '\'' +
                ", id='" + id + '\'' +
                ", img=" + img +
                ", item=" + item +
                ", messageCount='" + messageCount + '\'' +
                ", title='" + title + '\'' +
                ", user_id='" + user_id + '\'' +
                ", voice='" + voice + '\'' +
                '}';
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFoucsCount() {
        return foucsCount;
    }

    public void setFoucsCount(String foucsCount) {
        this.foucsCount = foucsCount;
    }

    private String id;
    private ArrayList<ImgData> img;
    private ItemData item;
    private String messageCount;
    private String title;
    private String user_id;
    private String voice;

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public List<CommentData> getComment() {
        return comment;
    }

    public void setComment(List<CommentData> comment) {
        this.comment = comment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<ImgData> getImg() {
        return img;
    }

    public void setImg(ArrayList<ImgData> img) {
        this.img = img;
    }

    public ItemData getItem() {
        return item;
    }

    public void setItem(ItemData item) {
        this.item = item;
    }

    public String getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(String messageCount) {
        this.messageCount = messageCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }
}
