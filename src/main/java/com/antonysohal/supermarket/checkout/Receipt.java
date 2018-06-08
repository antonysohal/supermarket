package com.antonysohal.supermarket.checkout;

import java.math.BigDecimal;

public interface Receipt {

    BigDecimal getTotal();

    BigDecimal getTotalDiscount();

    BigDecimal geTotalCostOfProducts();
}
