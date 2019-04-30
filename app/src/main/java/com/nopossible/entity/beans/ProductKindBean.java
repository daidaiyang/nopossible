package com.nopossible.entity.beans;

import java.io.Serializable;

public class ProductKindBean implements Serializable {


    private static final long serialVersionUID = -9075339832152431807L;
    private String id;
    private String icon;
    private String code;
    private String name;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
