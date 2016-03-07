package com.zhushan.bishengwang.Entry;

import java.util.List;

/**
 * Created by Administrator on 2016/1/8.
 */
public class Customer_data {
    private String addtime;
    private String auth_message;
    private String company_area;
    private String company_description;
    private String company_name;
    private String company_telephone;
    private List<Customer_gallery> customer_gallery;
    private String customer_name;
    private String id;
    private String img;
    private String img_thumb;
    private String lat;
    private String lng;
    private String mark;
    private String remark;
    private String status;
    private String user_id;

    @Override
    public String toString() {
        return "Customer_data{" +
                "addtime='" + addtime + '\'' +
                ", auth_message='" + auth_message + '\'' +
                ", company_area='" + company_area + '\'' +
                ", company_description='" + company_description + '\'' +
                ", company_name='" + company_name + '\'' +
                ", company_telephone='" + company_telephone + '\'' +
                ", customer_gallery=" + customer_gallery +
                ", customer_name='" + customer_name + '\'' +
                ", id='" + id + '\'' +
                ", img='" + img + '\'' +
                ", img_thumb='" + img_thumb + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", mark='" + mark + '\'' +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }

    public List<Customer_gallery> getCustomer_gallery() {
        return customer_gallery;
    }

    public void setCustomer_gallery(List<Customer_gallery> customer_gallery) {
        this.customer_gallery = customer_gallery;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getAuth_message() {
        return auth_message;
    }

    public void setAuth_message(String auth_message) {
        this.auth_message = auth_message;
    }

    public String getCompany_area() {
        return company_area;
    }

    public void setCompany_area(String company_area) {
        this.company_area = company_area;
    }

    public String getCompany_description() {
        return company_description;
    }

    public void setCompany_description(String company_description) {
        this.company_description = company_description;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_telephone() {
        return company_telephone;
    }

    public void setCompany_telephone(String company_telephone) {
        this.company_telephone = company_telephone;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
