package com.antonysohal.supermarket.discount;

import com.antonysohal.supermarket.product.Product;

/**
 * Discount which is applied when the both the product ans quantity is matched
 */
public interface ProductQuantityDiscount extends Discount {

    /**
     * The quantity to match to apply this discount
     *
     * @return the quantity
     */
    public Integer getQuantity();

    /**
     * The product to which this discount applies
     *
     * @return the product
     */
    public Product getProduct();

}
