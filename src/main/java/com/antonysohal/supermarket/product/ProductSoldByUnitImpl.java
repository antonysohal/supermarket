package com.antonysohal.supermarket.product;

import java.math.BigDecimal;

class ProductSoldByUnitImpl extends AbstractProductImpl implements ProductSoldByUnit {

    ProductSoldByUnitImpl(String name, BigDecimal price) {
        super(name, price);
    }
}
