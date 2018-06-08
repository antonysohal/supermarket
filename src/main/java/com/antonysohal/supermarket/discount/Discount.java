package com.antonysohal.supermarket.discount;

import com.antonysohal.supermarket.product.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface Discount {

    /**
     * Returns the value of discount
     *
     * @return a negative number
     */
    BigDecimal getValue();


    /**
     * Return unique name of discount
     *
     * @return
     */
    String getName();


    /**
     * The product(s) to which this discount applies
     *
     * @return the product
     */
    List<Product> getProducts();

    /**
     * Returns the criteria when this discount can be applied
     *
     * @return the product corresponding quantity
     */
    Map<Product, Integer> getCriteria();


    /**
     * Check if this discount is valid
     *
     * @param productWithQuantity the products with quantity
     * @return <tt>true</tt> if the all product exist in criteria and the criteria quantity is equal or less than
     * quantity passed, otherwise <tt>false</tt>
     */
    boolean canApply(Map<Product, Integer> productWithQuantity);
}
