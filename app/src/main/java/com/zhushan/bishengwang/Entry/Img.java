package com.zhushan.bishengwang.Entry;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/12/30.
 */
public class Img implements Parcelable {
    private String fromid;
    private String img_thumb;
    private String img;

    public Img(Parcel source) {
        fromid = source.readString();
        img_thumb = source.readString();
        img = source.readString();
    }

    @Override
    public String toString() {
        return "Img{" +
                "fromid='" + fromid + '\'' +
                ", img_thumb='" + img_thumb + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    public String getImg_thumb() {
        return img_thumb;
    }

    public void setImg_thumb(String img_thumb) {
        this.img_thumb = img_thumb;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
          dest.writeString(fromid);
        dest.writeString(img_thumb);
        dest.writeString(img);

    }

    public static  Creator CREATOR = new Creator<Img>() {
        @Override
        public Img createFromParcel(Parcel source) {
            return new Img(source) ;
        }

        @Override
        public Img[] newArray(int size) {
            return new Img[size];
        }
    };
}
