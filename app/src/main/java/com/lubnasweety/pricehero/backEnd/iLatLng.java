package com.lubnasweety.pricehero.backEnd;

import java.io.Serializable;

/**
 * Created by Asus on 2/14/2018.
 */

public class iLatLng implements Serializable {
    double latitude, longitude;

    public iLatLng() {
    }

    public iLatLng(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
