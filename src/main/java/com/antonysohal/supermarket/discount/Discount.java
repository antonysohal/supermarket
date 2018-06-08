package com.antonysohal.supermarket.discount;

import java.math.BigDecimal;

public interface Discount {

    /**
     * Returns the value of discount
     * @return a negative number
     */
    BigDecimal getValue();


    /**
     * Return unique name of discount
     *
     * @return
     */
    String getName();


}
