package com.antonysohal.supermarket.product;

import java.math.BigDecimal;

class ProductImpl implements Product {

    private BigDecimal price;

    private String name;

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

}
