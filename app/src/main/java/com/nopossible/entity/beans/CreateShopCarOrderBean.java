package com.nopossible.entity.beans;

import java.util.List;

public class CreateShopCarOrderBean {

    private String province_no;
    private String province_name;
    private String city_no;
    private String city_name;
    private String district_no;
    private String district_name;
    private String address;
    private String longitude;
    private String latitude;
    private List<OrderList> order_list;

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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public List<OrderList> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<OrderList> order_list) {
        this.order_list = order_list;
    }
}
