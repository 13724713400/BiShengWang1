package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/20.
 */
public class WeinxinLoginData {
    private String token;

    @Override
    public String toString() {
        return "WeinxinLoginData{" +
                "token='" + token + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
