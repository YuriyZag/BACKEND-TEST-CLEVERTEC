package org.example.repository;

import org.example.model.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class ProductRepository implements ProductRepositoryInterface {
    private final List<Product> products = new ArrayList<>();;

    {
        products.add(new Product(1L, "Product 1", true, 10.99f));
        products.add(new Product(2L, "Product 2", true, 21.99f));
        products.add(new Product(3L, "Product 3", 16.99f));
        products.add(new Product(4L, "Product 4", 12.99f));
        products.add(new Product(5L, "Product 5", 11.99f));
    }

    @Override
    public Product findById(Long id) throws Exception {
        Optional<Product> first = products.stream()
                .filter(product -> Objects.equals(product.getId(), id))
                .findFirst();
        return first.orElseThrow(Exception::new);
    }

    @Override
    public void readAllProducts(String path) throws IOException {
        try (Stream<String> lines = Files.lines(Path.of(path))){
            lines.forEach(line -> {
                String[] arr = line.split(",");
                long id = Long.parseLong(arr[0]);
                String name = arr[1];
                boolean dis = Boolean.parseBoolean(arr[2]);
                float price = Float.parseFloat(arr[3]);
                products.add(new Product(id, name, dis, price));
            });
        }
    }
}
