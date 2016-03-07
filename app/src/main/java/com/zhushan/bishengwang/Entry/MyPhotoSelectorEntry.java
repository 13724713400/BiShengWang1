package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/19.
 */
public class MyPhotoSelectorEntry {

    private String path;
    private int id;

    @Override
    public String toString() {
        return "MyPhotoSelectorEntry{" +
                "path='" + path + '\'' +
                ", id=" + id +
                '}';
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
