package com.nopossible.entity.beans;

import java.io.Serializable;
import java.util.List;

public class SplitOrderResultBean implements Serializable {

    private static final long serialVersionUID = -1905503335452566860L;
    private String total_money;
    private List<SplitOrder_orderList> order_list;

    public String getTotal_money() {
        return total_money;
    }

    public void setTotal_money(String total_money) {
        this.total_money = total_money;
    }

    public List<SplitOrder_orderList> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<SplitOrder_orderList> order_list) {
        this.order_list = order_list;
    }
}
