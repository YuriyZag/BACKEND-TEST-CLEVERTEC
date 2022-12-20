package org.example;

import org.example.configuration.StartConfigurer;
import org.example.service.ShopServiceImpl;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        System.out.println("Hello world!");
        ShopServiceImpl shopService = StartConfigurer.getShopService(args);
        shopService.printCashReceipt();



    }
}