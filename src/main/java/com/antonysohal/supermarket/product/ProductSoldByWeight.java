package com.antonysohal.supermarket.product;

/**
 * Represents a product sold by a weight unit.
 * The price of product is by unit weight.
 */
public interface ProductSoldByWeight extends Product {

    /**
     * The unit used to sell this product
     *
     * @return
     */
    WeightUnit getWeightUnit();

}
