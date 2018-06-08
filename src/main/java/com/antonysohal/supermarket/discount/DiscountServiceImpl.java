package com.antonysohal.supermarket.discount;

import com.antonysohal.supermarket.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DiscountServiceImpl implements DiscountService {

    private Map<String, Discount> discounts = new HashMap<>();

    private static DiscountService INSTANCE;

    private DiscountServiceImpl() {
    }

    public static DiscountService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DiscountServiceImpl();
        }
        return INSTANCE;
    }

    /**
     * @param productList
     * @return
     */
    public List<Discount> getDiscounts(List<Product> productList) {
        return discounts.values().stream()
                .filter(discount ->
                        discount.getProducts().stream()
                                .anyMatch(product -> productList.contains(product)))
                .sorted(Comparator.comparing(Discount::getValue).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Discount> getDiscounts() {
        return Collections.unmodifiableList(new ArrayList<>(discounts.values()));
    }

    @Override
    public Optional<Discount> createDiscount(String name, BigDecimal discountValue, Product product, Integer quantity) {
        if (discounts.containsKey(name)) {
            return Optional.empty();
        }

        Map<Product, Integer> criteria = new HashMap<>();
        criteria.put(product, quantity);

        Discount discount = new ProductQuantityDiscountImpl(name, criteria, discountValue);
        discounts.put(discount.getName(), discount);
        return Optional.of(discount);
    }

}
