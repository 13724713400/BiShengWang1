package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2015/12/30.
 */
public class UserGreetMentData {

    private String content;

    @Override
    public String toString() {
        return "UserGreetMentData{" +
                "content='" + content + '\'' +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
