package org.example.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CashReceipt {
    private final Long id;
    private final String name;
    private final LocalDateTime localDateTime;
    private final String cashier;
    private final List<Product> productList = new ArrayList<>();

    public CashReceipt(Long id, String name, LocalDateTime localDateTime, String cashier) {
        this.id = id;
        this.name = name;
        this.localDateTime = localDateTime;
        this.cashier = cashier;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getCashier() {
        return cashier;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
