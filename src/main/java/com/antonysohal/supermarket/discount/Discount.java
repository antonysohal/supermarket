package com.antonysohal.supermarket.discount;

import java.math.BigDecimal;

public interface Discount {

    /**
     * Returns the price of discount
     *
     * @return a negative number
     */
    BigDecimal getPrice();
}
