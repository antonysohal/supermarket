package com.antonysohal.supermarket.basket;

import com.antonysohal.supermarket.product.Product;

import java.util.List;

/**
 * A shopping basket
 */
public interface Basket {

    /**
     * Add product(s) to basket
     *
     * @param product the product to add
     * @param qty     the quantity
     * @return this basket
     */
    Basket add(Product product, Integer qty);

    /**
     * Return the content of the basket
     *
     * @return contents
     */
    List<Product> getContents();
}
