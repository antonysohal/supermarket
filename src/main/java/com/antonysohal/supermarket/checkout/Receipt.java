package com.antonysohal.supermarket.checkout;

import java.math.BigDecimal;

/**
 * Receipt
 */
public interface Receipt {

    /**
     * Returns the total to pay
     *
     * @return
     */
    BigDecimal getTotal();

    /**
     * Return the total of discount applied
     * @return
     */
    BigDecimal getTotalDiscount();

    /**
     * Return the total of product before any discount is applied
     * @return
     */
    BigDecimal geTotalCostOfProducts();
}
