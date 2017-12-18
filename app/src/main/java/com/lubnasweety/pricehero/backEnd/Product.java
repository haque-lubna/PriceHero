package com.lubnasweety.pricehero.backEnd;

/**
 * Created by Asus on 12/18/2017.
 */

public class Product {
    String productName;
    String productCategory;
    String imageAddress;

    public Product(String productName, String productCategory, String imageAddress) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.imageAddress = imageAddress;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
