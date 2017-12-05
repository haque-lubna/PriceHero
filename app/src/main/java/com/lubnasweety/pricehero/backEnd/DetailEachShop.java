package com.lubnasweety.pricehero.backEnd;

/**
 * Created by Asus on 11/23/2017.
 */

public class DetailEachShop {
    private double price;
    private String storeName;
    private String location;
    private String offer;
    private int availableProduct;




    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public int getAvailableProduct() {
        return availableProduct;
    }

    public void setAvailableProduct(int availableProduct) {
        this.availableProduct = availableProduct;
    }

    public DetailEachShop() {
    }

    public DetailEachShop(double price, String storeName, String location, String offer, int availableProduct) {
        this.price = price;
        this.storeName = storeName;
        this.location = location;
        this.offer = offer;
        this.availableProduct = availableProduct;
    }

}
