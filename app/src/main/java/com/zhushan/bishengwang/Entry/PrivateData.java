package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/16.
 */
public class PrivateData {

    private String addtime;
    private String g_id;
    private String head_img;
    private String id;
    private String message;
    private String nickname;
    private String r_id;

    @Override
    public String toString() {
        return "PrivateData{" +
                "addtime='" + addtime + '\'' +
                ", g_id='" + g_id + '\'' +
                ", head_img='" + head_img + '\'' +
                ", id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", nickname='" + nickname + '\'' +
                ", r_id='" + r_id + '\'' +
                '}';
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getG_id() {
        return g_id;
    }

    public void setG_id(String g_id) {
        this.g_id = g_id;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }
}
