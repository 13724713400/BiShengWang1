package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/5.
 */
public class Lt3 {

    private String condition;
    private String product_area;
    private String price_range;
    private String brand;
    private String product_date;
    private String is_service;

    @Override
    public String toString() {
        return "Lt3{" +
                "brand='" + brand + '\'' +
                ", condition='" + condition + '\'' +
                ", is_service='" + is_service + '\'' +
                ", price_range='" + price_range + '\'' +
                ", product_area='" + product_area + '\'' +
                ", product_date='" + product_date + '\'' +
                '}';
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getIs_service() {
        return is_service;
    }

    public void setIs_service(String is_service) {
        this.is_service = is_service;
    }

    public String getPrice_range() {
        return price_range;
    }

    public void setPrice_range(String price_range) {
        this.price_range = price_range;
    }

    public String getProduct_area() {
        return product_area;
    }

    public void setProduct_area(String product_area) {
        this.product_area = product_area;
    }

    public String getProduct_date() {
        return product_date;
    }

    public void setProduct_date(String product_date) {
        this.product_date = product_date;
    }
}
