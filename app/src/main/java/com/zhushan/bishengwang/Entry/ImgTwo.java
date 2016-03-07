package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/6.
 */
public class ImgTwo {

    private String fromid;
    private String img;
    private String img_thumb;

    @Override
    public String toString() {
        return "ImgTwo{" +
                "fromid='" + fromid + '\'' +
                ", img='" + img + '\'' +
                ", img_thumb='" + img_thumb + '\'' +
                '}';
    }

    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg_thumb() {
        return img_thumb;
    }

    public void setImg_thumb(String img_thumb) {
        this.img_thumb = img_thumb;
    }
}
