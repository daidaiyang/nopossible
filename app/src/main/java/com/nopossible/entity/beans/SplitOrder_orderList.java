package com.nopossible.entity.beans;

import java.io.Serializable;
import java.util.List;

public class SplitOrder_orderList implements Serializable {


    private static final long serialVersionUID = 5621213562416519526L;
    private String id;
    private String user_id;
    private String delivery_id;
    private long no;
    private String money;
    private String pay_money;
    private String pay_type;
    private String pay_account;
    private String pay_name;
    private String again_pay_money;
    private String again_pay_type;
    private String again_pay_account;
    private String again_pay_name;
    private String delivery_money;
    private String affirm_money;
    private String reject_money;
    private String replenish_money;
    private int status;
    private String province_no;
    private String province_name;
    private String city_no;
    private String city_name;
    private String district_no;
    private String district_name;
    private String address;
    private String take_contacts;
    private String take_tel_phone;
    private String delivery_way;
    private String delivery_time;
    private String valid;
    private String update_user;
    private String update_time;
    private String create_user;
    private String create_time;
    private String remark;
    private List<OrderLineBean> order_line;

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

    public String getDelivery_id() {
        return delivery_id;
    }

    public void setDelivery_id(String delivery_id) {
        this.delivery_id = delivery_id;
    }

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getPay_money() {
        return pay_money;
    }

    public void setPay_money(String pay_money) {
        this.pay_money = pay_money;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getPay_account() {
        return pay_account;
    }

    public void setPay_account(String pay_account) {
        this.pay_account = pay_account;
    }

    public String getPay_name() {
        return pay_name;
    }

    public void setPay_name(String pay_name) {
        this.pay_name = pay_name;
    }

    public String getAgain_pay_money() {
        return again_pay_money;
    }

    public void setAgain_pay_money(String again_pay_money) {
        this.again_pay_money = again_pay_money;
    }

    public String getAgain_pay_type() {
        return again_pay_type;
    }

    public void setAgain_pay_type(String again_pay_type) {
        this.again_pay_type = again_pay_type;
    }

    public String getAgain_pay_account() {
        return again_pay_account;
    }

    public void setAgain_pay_account(String again_pay_account) {
        this.again_pay_account = again_pay_account;
    }

    public String getAgain_pay_name() {
        return again_pay_name;
    }

    public void setAgain_pay_name(String again_pay_name) {
        this.again_pay_name = again_pay_name;
    }

    public String getDelivery_money() {
        return delivery_money;
    }

    public void setDelivery_money(String delivery_money) {
        this.delivery_money = delivery_money;
    }

    public String getAffirm_money() {
        return affirm_money;
    }

    public void setAffirm_money(String affirm_money) {
        this.affirm_money = affirm_money;
    }

    public String getReject_money() {
        return reject_money;
    }

    public void setReject_money(String reject_money) {
        this.reject_money = reject_money;
    }

    public String getReplenish_money() {
        return replenish_money;
    }

    public void setReplenish_money(String replenish_money) {
        this.replenish_money = replenish_money;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProvince_no() {
        return province_no;
    }

    public void setProvince_no(String province_no) {
        this.province_no = province_no;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_no() {
        return city_no;
    }

    public void setCity_no(String city_no) {
        this.city_no = city_no;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDistrict_no() {
        return district_no;
    }

    public void setDistrict_no(String district_no) {
        this.district_no = district_no;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getDelivery_way() {
        return delivery_way;
    }

    public void setDelivery_way(String delivery_way) {
        this.delivery_way = delivery_way;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
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

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<OrderLineBean> getOrder_line() {
        return order_line;
    }

    public void setOrder_line(List<OrderLineBean> order_line) {
        this.order_line = order_line;
    }
}
