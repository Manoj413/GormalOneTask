package com.gormal.gormalonetask.product.model;

import java.io.Serializable;

public class Product implements Serializable {
    String product_name,product_desc,product_quantity,product_price,user_mobile_no;

    public Product(String product_name, String product_desc, String product_quantity, String product_price, String user_mobile_no) {
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.product_quantity = product_quantity;
        this.product_price = product_price;
        this.user_mobile_no = user_mobile_no;
    }

    public Product() {

    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public String getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(String product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getUser_mobile_no() {
        return user_mobile_no;
    }

    public void setUser_mobile_no(String user_mobile_no) {
        this.user_mobile_no = user_mobile_no;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_name='" + product_name + '\'' +
                ", product_desc='" + product_desc + '\'' +
                ", product_quantity='" + product_quantity + '\'' +
                ", product_price='" + product_price + '\'' +
                ", user_mobile_no='" + user_mobile_no + '\'' +
                '}';
    }
}
