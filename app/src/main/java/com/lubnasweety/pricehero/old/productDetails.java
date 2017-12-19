package com.lubnasweety.pricehero.old;

import java.io.Serializable;

/**
 * Created by Asus on 11/23/2017.
 */

public class productDetails implements Serializable {
    private String productName;
    private String productDescription;
    private Integer imagetName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getImagetName() {
        return imagetName;
    }

    public void setImagetName(Integer imagetName) {
        this.imagetName = imagetName;
    }

    public productDetails() {
    }

    public productDetails(String productName, String productDescription, Integer imagetName) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.imagetName = imagetName;
    }
}
