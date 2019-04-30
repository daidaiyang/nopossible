package com.nopossible.entity.beans;

import java.io.Serializable;

public class OrderLineBean implements Serializable {

    private static final long serialVersionUID = 6882799561857869818L;
    private String id;
    private String user_id;
    private String order_id;
    private String product_id;
    private String num;
    private String unit_price;
    private String discounts;
    private String money;
    private String affirm_type;
    private String affirm_num;
    private String affirm_money;
    private String valid;
    private String update_user;
    private long update_time;
    private String create_user;
    private long create_time;
    private String remark;
    private OrderLine_Product product;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(String unit_price) {
        this.unit_price = unit_price;
    }

    public String getDiscounts() {
        return discounts;
    }

    public void setDiscounts(String discounts) {
        this.discounts = discounts;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getAffirm_type() {
        return affirm_type;
    }

    public void setAffirm_type(String affirm_type) {
        this.affirm_type = affirm_type;
    }

    public String getAffirm_num() {
        return affirm_num;
    }

    public void setAffirm_num(String affirm_num) {
        this.affirm_num = affirm_num;
    }

    public String getAffirm_money() {
        return affirm_money;
    }

    public void setAffirm_money(String affirm_money) {
        this.affirm_money = affirm_money;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(String update_user) {
        this.update_user = update_user;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public OrderLine_Product getProduct() {
        return product;
    }

    public void setProduct(OrderLine_Product product) {
        this.product = product;
    }
}
