package com.zhushan.bishengwang.Entry;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/1/5.
 */
public class ImgData implements Parcelable {

    private String fromid;
    private String img;
    private String img_thumb;

    public ImgData(Parcel source) {
        fromid = source.readString();
        img = source.readString();
        img_thumb = source.readString();
    }

    @Override
    public String toString() {
        return "ImgData{" +
                "fromid='" + fromid + '\'' +
                ", img='" + img + '\'' +
                ", img_thumb='" + img_thumb + '\'' +
                '}';
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

    public String getFromid() {
        return fromid;
    }

    public void setFromid(String fromid) {
        this.fromid = fromid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fromid);
        dest.writeString(img);
        dest.writeString(img_thumb);
    }

    public static Creator CREATOR = new Creator<ImgData>() {
        @Override
        public ImgData createFromParcel(Parcel source) {
            return new ImgData(source);
        }

        @Override
        public ImgData[] newArray(int size) {
            return new ImgData[size];
        }

    };


}
