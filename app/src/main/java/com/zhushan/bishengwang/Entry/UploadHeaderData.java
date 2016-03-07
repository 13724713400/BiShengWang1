package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2015/12/25.
 */
public class UploadHeaderData {

    private String token;

    @Override
    public String toString() {
        return "UploadHeaderData{" +
                "token=" + token +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
