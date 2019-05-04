package com.nopossible.entity.beans;

import java.io.Serializable;

public class MyApplyBean implements Serializable {
    private static final long serialVersionUID = 5256009166036478552L;

    private String id;
    private String user_id;
    private String kind_code;
    private String kind_name;
    private String need_kind_name;
    private String brand;
    private BaseImageList images;
    private String contacts;
    private String phone;
    private String remark;
    private String total_user;
    private int valid;
    private String update_user;
    private long update_time;
    private String create_user;
    private long create_time;


    public String getTotal_user() {
        return total_user;
    }

    public void setTotal_user(String total_user) {
        this.total_user = total_user;
    }

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

    public String getKind_code() {
        return kind_code;
    }

    public void setKind_code(String kind_code) {
        this.kind_code = kind_code;
    }

    public String getKind_name() {
        return kind_name;
    }

    public void setKind_name(String kind_name) {
        this.kind_name = kind_name;
    }

    public String getNeed_kind_name() {
        return need_kind_name;
    }

    public void setNeed_kind_name(String need_kind_name) {
        this.need_kind_name = need_kind_name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BaseImageList getImages() {
        return images;
    }

    public void setImages(BaseImageList images) {
        this.images = images;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
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
}
