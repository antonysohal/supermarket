package com.antonysohal.supermarket.discount;

import com.antonysohal.supermarket.product.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DiscountService {

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


    /**
     * Create and add combo discount.
     *
     * @param name          unique name of discount
     * @param discountValue price off
     * @param criteria      the criteria for discount with product and corresponding quantity
     * @return the discount or <tt>Optional.EMPTY</tt> if product exist with name passed.
     */
    Optional<Discount> createDiscount(String name, BigDecimal discountValue, Map<Product, Integer> criteria);
}
