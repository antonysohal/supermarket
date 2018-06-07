package com.antonysohal.supermarket;

import java.math.BigDecimal;

public interface Receipt {

    BigDecimal getTotal();

    BigDecimal getTotalDiscount();

    BigDecimal geTotalCostOfProducts();
}
