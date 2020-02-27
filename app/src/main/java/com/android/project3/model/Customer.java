package com.android.project3.model;

import java.util.HashMap;

/**
 * Created by keban on 4/17/2017.
 */
public class Customer {

    private Builder builder;

    public Customer(Builder builder){
        this.builder = builder;
    }

    public Builder getBuilder() {
        return builder;
    }

    public static class Builder{

        private String id;
        private String name;
        private String vehicle;
        private String type;
        private Warranty warranty;
        private HashMap userRoad;

        public String getId() {
            return id;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public String getVehicle() {
            return vehicle;
        }

        public String getType() {
            return type;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setVehicle(String vehicle) {
            this.vehicle = vehicle;
            return this;
        }

        public Warranty getWarranty() {
            return warranty;
        }

        public Builder setWarranty(Warranty warranty) {
            this.warranty = warranty;
            return this;
        }

        public HashMap getUserRoad() {
            return userRoad;
        }

        public Builder setUserRoad(HashMap userRoad) {
            this.userRoad = userRoad;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

}
