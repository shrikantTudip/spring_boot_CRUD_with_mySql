package com.yuktamedia.model;

public class Product {
    private Integer pid;

    private String productName;
    private String productImage;
    private String productColor;

    public Integer getPid() {
        return pid;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

}
