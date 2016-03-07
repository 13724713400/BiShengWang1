package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/9.
 */
public class Customer_gallery {
    private String addtime;
    private String customer_id;
    private String id;
    private String img;
    private String img_thumb;
    private String type;

    @Override
    public String toString() {
        return "Customer_gallery{" +
                "addtime='" + addtime + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", id='" + id + '\'' +
                ", img='" + img + '\'' +
                ", img_thumb='" + img_thumb + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
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

    public String getImg_thumb() {
        return img_thumb;
    }

    public void setImg_thumb(String img_thumb) {
        this.img_thumb = img_thumb;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
