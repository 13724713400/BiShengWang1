package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2015/12/31.
 */
public class UserInfoData {

    private String age;
    private String description;
    private String head_thumb;
    private String label_list;
    private String nickname;
    private String point;
    private String sex;
    private int user_type;

    @Override
    public String toString() {
        return "UserInfoData{" +
                "age='" + age + '\'' +
                ", description='" + description + '\'' +
                ", head_thumb='" + head_thumb + '\'' +
                ", label_list='" + label_list + '\'' +
                ", nickname='" + nickname + '\'' +
                ", point='" + point + '\'' +
                ", sex='" + sex + '\'' +
                ", user_type=" + user_type +
                '}';
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHead_thumb() {
        return head_thumb;
    }

    public void setHead_thumb(String head_thumb) {
        this.head_thumb = head_thumb;
    }

    public String getLabel_list() {
        return label_list;
    }

    public void setLabel_list(String label_list) {
        this.label_list = label_list;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }
}
