package com.nopossible.entity.beans;

import java.util.List;

public class OrderList {
    private String delivery_id;
    private String no;
    private String take_contacts;
    private String take_tel_phone;
    private String delivery_time;
    private List<OrderLine> order_line;


    public String getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(String delivery_id) {
        this.delivery_id = delivery_id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTake_contacts() {
        return take_contacts;
    }

    public void setTake_contacts(String take_contacts) {
        this.take_contacts = take_contacts;
    }

    public String getTake_tel_phone() {
        return take_tel_phone;
    }

    public void setTake_tel_phone(String take_tel_phone) {
        this.take_tel_phone = take_tel_phone;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public List<OrderLine> getOrder_line() {
        return order_line;
    }

    public void setOrder_line(List<OrderLine> order_line) {
        this.order_line = order_line;
    }
}
