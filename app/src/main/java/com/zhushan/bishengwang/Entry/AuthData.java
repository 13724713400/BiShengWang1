package com.zhushan.bishengwang.Entry;

/**
 * Created by Administrator on 2016/1/8.
 */
public class AuthData {

    private Customer_data customer_data;
    private People_data people_data;

    @Override
    public String toString() {
        return "AuthData{" +
                "customer_data=" + customer_data +
                ", people_data=" + people_data +
                '}';
    }

    public Customer_data getCustomer_data() {
        return customer_data;
    }

    public void setCustomer_data(Customer_data customer_data) {
        this.customer_data = customer_data;
    }

    public People_data getPeople_data() {
        return people_data;
    }

    public void setPeople_data(People_data people_data) {
        this.people_data = people_data;
    }
}
