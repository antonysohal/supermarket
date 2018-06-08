package com.antonysohal.supermarket.basket;

import com.antonysohal.supermarket.product.Product;

/**
 * A shopping basket
 */
public interface Basket {

    Basket add(Product product, Integer qty);
}
