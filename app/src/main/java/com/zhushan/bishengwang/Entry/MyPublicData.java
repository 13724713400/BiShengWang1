package com.zhushan.bishengwang.Entry;

import java.util.List;

/**
 * Created by Administrator on 2016/1/6.
 */
public class MyPublicData {

    private String addtime;
    private String area;
    private String cate_id;
    private String content;
    private String foucsCount;
    private String hit;
    private String id;
    private List<ImgTwo> img;
    private String messageCount;
    private int status;
    private String title;

    @Override
    public String toString() {
        return "MyPublicData{" +
                "addtime='" + addtime + '\'' +
                ", area='" + area + '\'' +
                ", cate_id='" + cate_id + '\'' +
                ", content='" + content + '\'' +
                ", foucsCount='" + foucsCount + '\'' +
                ", hit='" + hit + '\'' +
                ", id='" + id + '\'' +
                ", img=" + img +
                ", messageCount='" + messageCount + '\'' +
                ", status=" + status +
                ", title='" + title + '\'' +
                '}';
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFoucsCount() {
        return foucsCount;
    }

    public void setFoucsCount(String foucsCount) {
        this.foucsCount = foucsCount;
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

    public List<ImgTwo> getImg() {
        return img;
    }

    public void setImg(List<ImgTwo> img) {
        this.img = img;
    }

    public String getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(String messageCount) {
        this.messageCount = messageCount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
