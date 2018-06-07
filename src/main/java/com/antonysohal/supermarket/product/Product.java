package com.antonysohal.supermarket.product;

import java.math.BigDecimal;

/**
 * Representation of a Product
 */
public interface Product {

    /**
     * Returns price of this product
     *
     * @return price
     */
    BigDecimal getPrice();

    /**
     * Returns name of product
     *
     * @return name
     */
    String getName();
}
