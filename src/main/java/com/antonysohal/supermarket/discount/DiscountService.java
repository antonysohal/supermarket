package com.antonysohal.supermarket.discount;

import com.antonysohal.supermarket.product.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DiscountService {

    /**
     * Return all discounts
     *
     * @return discounts
     */
    List<Discount> getDiscounts();


    Optional<Discount> createDiscount(String name, BigDecimal discountValue, Product product, Integer quantity);
}
