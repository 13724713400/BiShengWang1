package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/8.
 */
public class MyMessageData {
    private String addtime;
    private String comment;
    private String head_thumb;
    private String id;
    private String nickname;
    private String sex;
    private String user_send_id;
    private String user_type;

    @Override
    public String toString() {
        return "MyMessageData{" +
                "addtime='" + addtime + '\'' +
                ", comment='" + comment + '\'' +
                ", head_thumb='" + head_thumb + '\'' +
                ", id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", user_send_id='" + user_send_id + '\'' +
                ", user_type='" + user_type + '\'' +
                '}';
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUser_send_id() {
        return user_send_id;
    }

    public void setUser_send_id(String user_send_id) {
        this.user_send_id = user_send_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
}
