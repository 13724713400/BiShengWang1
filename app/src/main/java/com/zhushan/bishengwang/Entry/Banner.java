package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/4.
 */
public class Banner {
    private String img;
    private String link;
    @Override
    public String toString() {
        return "Banner{" +
                "img='" + img + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
