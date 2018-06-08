package com.antonysohal.supermarket.discount;

import com.antonysohal.supermarket.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DiscountServiceImpl implements DiscountService {

    private Map<String, Discount> discounts = new HashMap<>();

    private static DiscountService INSTANCE;

    private DiscountServiceImpl() {
    }

    public static DiscountService getInstace() {
        if (INSTANCE == null) {
            INSTANCE = new DiscountServiceImpl();
        }
        return INSTANCE;
    }

    @Override
    public List<Discount> getDiscounts() {
        return new ArrayList<>(discounts.values());
    }

    @Override
    public Optional<Discount> createDiscount(String name, BigDecimal discountValue, Product product, Integer quantity) {
        if (discounts.containsKey(name)) {
            return Optional.empty();
        }
        Discount discount = new ProductQuantityDiscountImpl(name, product, quantity, discountValue);
        discounts.put(discount.getName(), discount);
        return Optional.of(discount);
    }

}
