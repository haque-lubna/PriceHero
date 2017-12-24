package com.lubnasweety.pricehero.backEnd;

/**
 * Created by Asus on 12/21/2017.
 */

public class Notification {
    String productName;
    String productCategory;
    String shopKey;
    Integer productNeeded;
    String buyer;
    String imagePath;
    String pendingKey="";
    String date="";
    String acceptState="";

    public Notification() {
    }

    public Notification(String productName, String productCategory, String shopKey, Integer productNeeded, String buyer, String imagePath, String pendingKey) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.shopKey = shopKey;
        this.productNeeded = productNeeded;
        this.buyer = buyer;
        this.imagePath = imagePath;
        this.pendingKey = pendingKey;
    }

    public String getPendingKey() {
        return pendingKey;
    }

    public void setPendingKey(String pendingKey) {
        this.pendingKey = pendingKey;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAcceptState() {
        return acceptState;
    }

    public void setAcceptState(String acceptState) {
        this.acceptState = acceptState;
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

    public String getShopKey() {
        return shopKey;
    }

    public void setShopKey(String shopKey) {
        this.shopKey = shopKey;
    }

    public Integer getProductNeeded() {
        return productNeeded;
    }

    public void setProductNeeded(Integer productNeeded) {
        this.productNeeded = productNeeded;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
