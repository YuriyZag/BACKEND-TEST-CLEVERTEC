package org.example.service;

import org.example.model.DiscountCard;
import org.example.model.Product;
import org.example.repository.CashReceiptRepositoryInterface;
import org.example.repository.DiscountCardRepository;
import org.example.repository.ProductRepository;
import org.example.repository.ProductRepositoryInterface;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopServiceImpl {

    private final ProductRepositoryInterface productRepository;
    private final DiscountCardRepository discountCardRepository;
    private final CashReceiptRepositoryInterface cashReceiptRepository;

    private HashMap<Long, Integer> products;
    private Long idDiscountCard;
    private String out;
    private List<String> doc = new ArrayList<>();

    public void setProducts(HashMap<Long, Integer> products) {
        this.products = products;
    }

    public void setIdDiscountCard(Long idDiscountCard) {
        this.idDiscountCard = idDiscountCard;
    }

    public ShopServiceImpl(ProductRepositoryInterface productRepository, DiscountCardRepository discountCardRepository, CashReceiptRepositoryInterface cashReceiptRepository) {
        this.productRepository = productRepository;
        this.discountCardRepository = discountCardRepository;
        this.cashReceiptRepository = cashReceiptRepository;
    }

    public void prepareCashReceipt() {
        String line = "===================================================================";
        DiscountCard discountCard = null;
        float total = 0;
        float totalDiscount = 0;
        try {
            discountCard = discountCardRepository.findById(idDiscountCard);
        } catch (Exception e) {
        }
        int c = 0;
        doc.add(line);

        for (Map.Entry<Long, Integer> entry : products.entrySet()) {
            try {
                Product byId = productRepository.findById(entry.getKey());
                double price = byId.getPrice();
                Integer quantity = entry.getValue();
                if (quantity >= 5 && byId.isDiscount()){
                    price = byId.getPrice() * 0.9;
                }
                if (discountCard != null){
                    price = price * (100-discountCard.getDiscountPercent())/100;
                    String s = String.format("|| %2d  |  %-20s |  %5.2f |  %2d  | %6.2f | %6.2f ||", ++c, byId.getName(), price, quantity ,price * quantity, (price- byId.getPrice())*quantity);
                    doc.add(s);
                } else {
                    String s = String.format("|| %2d  |  %-20s |  %5.2f |  %2d  | %6.2f | %6s ||", ++c, byId.getName(), price, quantity ,price * quantity,"");
                    doc.add(s);
                }
                total += price * quantity;
                totalDiscount += (byId.getPrice()-price)*quantity;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        doc.add(line);
        String s = String.format("|| DISCOUNT                                            %9.2f ||", totalDiscount );
        doc.add(s);
        s = String.format("|| TOTAL                                      %9.2f          ||", total );
        doc.add(s);
        doc.add(line);
    }

    public void printCashReceipt() {
        this.prepareCashReceipt();
        if(getOut() == null){
            for (String s : doc){
                System.out.println(s);
            }
        } else {
            Path path = Paths.get(getOut());
            try {
                Files.write(path, doc, StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException("Файл не найден!");
            }
        }

    }

    public void setOut(String out) {
        this.out = out;
    }

    public String getOut() {
        return out;
    }

    public void addProduct(String file){
        try {
            productRepository.readAllProducts(file);
        } catch (Exception e) {
            throw new RuntimeException("Файл с данными отсутствует");
        }
    }
}
