package org.example.model;

public class Product {
    private final Long id;
    private final String name;
    private final boolean discount;
    private final Float price;

    public Product(Long id, String name, boolean discount, Float price) {
        this.id = id;
        this.name = name;
        this.discount = discount;
        this.price = price;
    }

    public Product(Long id, String name, Float price) {
        this.id = id;
        this.name = name;
        this.discount = false;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isDiscount() {
        return discount;
    }

    public Float getPrice() {
        return price;
    }
}
