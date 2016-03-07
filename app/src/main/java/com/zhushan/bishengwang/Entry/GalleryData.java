package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/9.
 */
public class GalleryData {
    private String id;
    private String mid_img;

    @Override
    public String toString() {
        return "GalleryData{" +
                "id='" + id + '\'' +
                ", mid_img='" + mid_img + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMid_img() {
        return mid_img;
    }

    public void setMid_img(String mid_img) {
        this.mid_img = mid_img;
    }
}
