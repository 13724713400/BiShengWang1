package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2015/12/28.
 */
public class LoginData {

    private String token;

    @Override
    public String toString() {
        return "LoginData{" +
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
