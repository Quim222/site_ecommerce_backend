package com.eCommerce.shoppingSite.tables.product;

public enum ProductIdenty {

    MASCULINO("masculino"),
    FEMININO("feminino");

    private String role;

    ProductIdenty(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
