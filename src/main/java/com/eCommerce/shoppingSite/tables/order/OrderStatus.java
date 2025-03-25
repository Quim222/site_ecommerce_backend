package com.eCommerce.shoppingSite.tables.order;

public enum OrderStatus {

    PENDING("pending"),
    CONFIRMED("confirmed"),
    DELIVERED("delivered"),
    CANCELED("canceled");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
