package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/5.
 */
public class Lt2 {

    private String finish_time;
    private String money;
    private String number;

    @Override
    public String toString() {
        return "Lt2{" +
                "finish_time='" + finish_time + '\'' +
                ", money='" + money + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    public String getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(String finish_time) {
        this.finish_time = finish_time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
