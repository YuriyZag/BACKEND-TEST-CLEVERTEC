package org.example.model;

public class DiscountCard {
    private final Long id;
    private final String name;
    private final Integer discountPercent;

    public DiscountCard(Long id, String name, Integer discountPercent) {
        this.id = id;
        this.name = name;
        this.discountPercent = discountPercent;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }
}
