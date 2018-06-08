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

    /**
     * Return all discount for the list of product passed
     *
     * @param productList the product
     * @return a list of applicable discounts
     */
    List<Discount> getDiscounts(List<Product> productList);

    /**
     * Create and add discount
     *
     * @param name          unique name of discount
     * @param discountValue price off
     * @param product       the product
     * @param quantity      the quantity
     * @return the discount or <tt>Optional.EMPTY</tt> if product exist with name passed.
     */
    Optional<Discount> createDiscount(String name, BigDecimal discountValue, Product product, Integer quantity);
}
