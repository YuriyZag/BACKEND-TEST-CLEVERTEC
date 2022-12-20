package org.example.configuration;

import org.example.repository.*;
import org.example.service.ShopServiceImpl;

import java.util.HashMap;

public class StartConfigurer {

    public static ShopServiceImpl getShopService(String[] args){
        ShopServiceImpl shopService = new ShopServiceImpl(getProductRepo(), getDiscountCountRepo(), getCashReceiptRepo());

        HashMap<Long, Integer> productMap = new HashMap<>();
        long discountId = 0L;
        for (String arg : args) {
            if (arg.matches("\\d+-\\d+")) {
                String[] split = arg.split("-");
                productMap.put(Long.parseLong(split[0]), Integer.parseInt(split[1]));
            } else if (arg.matches("card-\\d+")) {
                String[] split = arg.split("-");
                discountId = Long.parseLong(split[1]);
                shopService.setIdDiscountCard(discountId);
            } else if (arg.startsWith("-out:")){
                shopService.setOut(arg.replace("-out:", ""));
            } else if (arg.startsWith("-f:")) {
                shopService.addProduct(arg.replace("-f:",""));
            }
        }

        shopService.setProducts(productMap);

        return shopService;
    }

    private static ProductRepositoryInterface getProductRepo(){
        return new ProductRepository();
    }

    private static CashReceiptRepositoryInterface getCashReceiptRepo(){
        return new CashReceiptRepository();
    }

    private static DiscountCardRepository getDiscountCountRepo(){
        return new DiscountCardRepository();
    }

}
