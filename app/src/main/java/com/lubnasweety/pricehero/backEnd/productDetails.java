package com.lubnasweety.pricehero.backEnd;

import java.io.Serializable;

/**
 * Created by Asus on 11/23/2017.
 */

public class productDetails implements Serializable {
    private String productName;
    private String companyName;
    private Integer imagetName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getImagetName() {
        return imagetName;
    }

    public void setImagetName(Integer imagetName) {
        this.imagetName = imagetName;
    }

    public productDetails() {
    }

    public productDetails(String productName, String companyName, Integer imagetName) {
        this.productName = productName;
        this.companyName = companyName;
        this.imagetName = imagetName;
    }
}
