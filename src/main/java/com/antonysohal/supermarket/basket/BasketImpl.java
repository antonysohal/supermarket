package com.antonysohal.supermarket.basket;

import com.antonysohal.supermarket.product.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Basket Implementation
 */
class BasketImpl implements Basket {

    List<Product> basket = new ArrayList<>();

    BasketImpl() {
    }

    @Override
    public Basket add(Product product, Integer qty) {
        for (int i = 0; i < qty; i++) {
            basket.add(product);
        }
        return this;
    }

    public List<Product> getContents() {
        return Collections.unmodifiableList(basket);
    }

    @Override
    public String toString() {
        return "basket=" + basket;
    }
}
