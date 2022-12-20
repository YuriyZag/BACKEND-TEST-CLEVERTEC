package org.example.repository;

import org.example.model.DiscountCard;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DiscountCardRepository {
    private final List<DiscountCard> discountCardList = new ArrayList<>();
    {
        discountCardList.add(new DiscountCard(1234L, "Owner", 7));
    }

    public DiscountCard findById(Long id) {
        Optional<DiscountCard> first = discountCardList.stream()
                .filter(discountCard -> Objects.equals(discountCard.getId(), id))
                .findFirst();
        return first.orElseThrow(()-> new RuntimeException("Код скидочной карты отсутствует в БД"));
    }




}
