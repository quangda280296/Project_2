package com.android.project3.model;

/**
 * Created by keban on 5/6/2017.
 */

public class NLocation {

    public Builder builder;

    public NLocation(Builder builder)
    {
        this.builder = builder;
    }

    public Builder getBuilder() {
        return builder;
    }

    public static class Builder{

        private String name;
        private String address;
        private double latitude;
        private double longitude;

        public String getAddress() {
            return address;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public String getName() {
            return name;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public double getLatitude() {
            return latitude;
        }

        public Builder setLatitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public double getLongitude() {
            return longitude;
        }

        public Builder setLongitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public NLocation build() {
            return new NLocation(this);
        }
    }


}
