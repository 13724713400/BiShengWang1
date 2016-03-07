package com.zhushan.bishengwang.Entry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/12.
 */
public class MyPhotoData {
    private String addtime;
    private String description;
    private ArrayList<GalleryDataTwo> gallery;
    private String id;
    private String img;
    private String user_id;

    @Override
    public String toString() {
        return "MyPhotoData{" +
                "addtime='" + addtime + '\'' +
                ", description='" + description + '\'' +
                ", gallery=" + gallery +
                ", id='" + id + '\'' +
                ", img='" + img + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public ArrayList<GalleryDataTwo> getGallery() {
        return gallery;
    }

    public void setGallery(ArrayList<GalleryDataTwo> gallery) {
        this.gallery = gallery;
    }

    public String getAddtime() {
        return addtime;
    }
    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
