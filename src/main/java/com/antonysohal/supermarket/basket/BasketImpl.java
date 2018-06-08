package com.antonysohal.supermarket.basket;

import com.antonysohal.supermarket.product.Product;

import java.util.ArrayList;
import java.util.List;

public class BasketImpl implements Basket {

    List<Product> basket = new ArrayList<>();


    BasketImpl() {
    }

    @Override
    public Basket add(Product product, Integer qty) {
        for (int i = 0; qty <= i; i++) {
            basket.add(product);
        }
        return this;
    }
}
