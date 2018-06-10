package com.antonysohal.supermarket.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private static ProductService INSTANCE;

    protected Map<String, Product> inventory = new HashMap<>();

    public static ProductService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductServiceImpl();
        }
        return INSTANCE;
    }

    private ProductServiceImpl() {
    }

    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(inventory.values());
    }

    @Override
    public Optional<Product> getProduct(String name) {
        if (inventory.containsKey(name)) {
            return Optional.of(inventory.get(name));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Product> createProduct(String name, BigDecimal price, WeightUnit weightUnit) {
        if (inventory.containsKey(name)) {
            return Optional.empty();
        }

        Product product;
        if (weightUnit == null) {
            product = new ProductSoldByUnitImpl(name, price);
        } else {
            product = new ProductSoldByWeightImpl(name, price, weightUnit);
        }
        inventory.put(product.getName(), product);
        return Optional.of(product);
    }
}
