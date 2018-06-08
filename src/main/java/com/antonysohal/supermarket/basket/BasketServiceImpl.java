package com.antonysohal.supermarket.basket;

public class BasketServiceImpl implements BasketService {

    private static BasketService SINGLETON;

    BasketServiceImpl() {
    }

    public static BasketService getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new BasketServiceImpl();
        }
        return SINGLETON;
    }

    @Override
    public Basket createBasket() {
        return new BasketImpl();
    }
}
