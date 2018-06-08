package com.antonysohal.supermarket.discount;

import java.util.List;

public interface DiscountService {

    /**
     * Return all discounts
     *
     * @return discounts
     */
    List<Discount> getDiscounts();


    Discount addDiscount(Discount discount);

}
