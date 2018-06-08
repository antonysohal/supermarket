package com.antonysohal.supermarket.checkout;

import com.antonysohal.supermarket.basket.Basket;

public interface CheckoutService {

    /**
     * Checkout basket and apply any discounts
     *
     * @param basket the basket to checkout
     * @return the receipt
     */
    Receipt checkout(Basket basket);
}
