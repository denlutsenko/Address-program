package com.entity;

import java.util.Objects;

/**
 * Created by lutsenko.d on 04.05.2017.
 */
public class StoreAddress {
    private String storeAddress;
    private double longitude;
    private double latitude;

    public StoreAddress() {
        storeAddress = "";
        longitude = 0.00;
        latitude = 0.00;
    }

    public StoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public StoreAddress(String storeAddress, double longitude, double latitude) {
        this.storeAddress = storeAddress;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }


    @Override
    public String toString() {
        return "StoreAddress{" +
                "storeAddress='" + storeAddress + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreAddress that = (StoreAddress) o;
        return Objects.equals(storeAddress, that.storeAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeAddress);
    }
}
