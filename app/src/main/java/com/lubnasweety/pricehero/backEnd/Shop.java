package com.lubnasweety.pricehero.backEnd;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by Asus on 12/15/2017.
 */

public class Shop implements Serializable {
    String productDescription;
    String storeName;
    String storeLocation;
    String productQuantity;
    String productOffers;
    Double productPrice;
    String sellerUid;
    String imageUrl;
    LatLng latLng;


    public Shop() {

    }

    public Shop(String productDescription, String storeName, String storeLocation, String productQuantity, String productOffers, Double productPrice, String sellerUid, String imageUrl,LatLng latLng) {
        this.productDescription = productDescription;
        this.storeName = storeName;
        this.storeLocation = storeLocation;
        this.productQuantity = productQuantity;
        this.productOffers = productOffers;
        this.productPrice = productPrice;
        this.sellerUid = sellerUid;
        this.imageUrl = imageUrl;
        this.latLng=latLng;

    }
    public  Shop(LatLng point){
        latLng=point;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductOffers() {
        return productOffers;
    }

    public void setProductOffers(String productOffers) {
        this.productOffers = productOffers;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getSellerUid() {
        return sellerUid;
    }

    public void setSellerUid(String sellerUid) {
        this.sellerUid = sellerUid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public LatLng getLatLng(){
        return latLng;
    }
    public void setLatLng(LatLng latLng){
        this.latLng=latLng;
    }

}
