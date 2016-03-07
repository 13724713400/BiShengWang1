package com.zhushan.bishengwang.Entry;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/1/12.
 */
public class GalleryDataTwo implements Parcelable {

    private String album_id;
    private String img;
    private String mid_img;

    public GalleryDataTwo(Parcel source) {
        album_id =  source.readString();
        img =  source.readString();
        mid_img =  source.readString();
    }

    @Override
    public String toString() {
        return "GalleryDataTwo{" +
                "album_id='" + album_id + '\'' +
                ", img='" + img + '\'' +
                ", mid_img='" + mid_img + '\'' +
                '}';
    }

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMid_img() {
        return mid_img;
    }

    public void setMid_img(String mid_img) {
        this.mid_img = mid_img;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
     dest.writeString(album_id);
        dest.writeString(img);
        dest.writeString(mid_img);
    }

   public static Creator CREATOR = new Creator<GalleryDataTwo>() {
        @Override
        public GalleryDataTwo createFromParcel(Parcel source) {
            return new GalleryDataTwo(source);
        }

        @Override
        public GalleryDataTwo[] newArray(int size) {
            return new GalleryDataTwo[size];
        }
    };
}
