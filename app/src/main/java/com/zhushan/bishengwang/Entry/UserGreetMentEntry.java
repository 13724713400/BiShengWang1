package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2015/12/30.
 */
public class UserGreetMentEntry {

    private int code;

    private UserGreetMentData data;

    private String message;

    @Override
    public String toString() {
        return "UserGreetMentEntry{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UserGreetMentData getData() {
        return data;
    }

    public void setData(UserGreetMentData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
