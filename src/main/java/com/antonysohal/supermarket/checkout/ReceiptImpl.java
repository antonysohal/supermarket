package com.antonysohal.supermarket.checkout;

import com.antonysohal.supermarket.basket.Basket;
import com.antonysohal.supermarket.discount.Discount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

class ReceiptImpl implements Receipt {

    private BigDecimal total;
    private BigDecimal totalDiscount;
    private BigDecimal totalCostOfProducts;
    private Basket basket;
    private List<Discount> discountsApplied;

    ReceiptImpl(Basket basket, List<Discount> discountsApplied) {
        setBasket(basket);
        setDiscountsApplied(discountsApplied);
        calculate();
    }

    private void calculate() {
        Double totalDis = discountsApplied.stream().collect(Collectors.summingDouble(d -> d.getValue().doubleValue()));
        Double totalCost = basket.getContents().stream().collect(Collectors.summingDouble(p -> p.getPrice().doubleValue()));
        setTotalDiscount(BigDecimal.valueOf(totalDis).setScale(2, RoundingMode.HALF_EVEN));
        setTotalCostOfProducts(BigDecimal.valueOf(totalCost).setScale(2, RoundingMode.HALF_EVEN));
        setTotal(totalCostOfProducts.add(totalDiscount));
    }

    public List<Discount> getDiscountsApplied() {
        return discountsApplied;
    }

    public void setDiscountsApplied(List<Discount> discountsApplied) {
        this.discountsApplied = discountsApplied;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public BigDecimal getTotalCostOfProducts() {
        return totalCostOfProducts;
    }

    public void setTotalCostOfProducts(BigDecimal totalCostOfProducts) {
        this.totalCostOfProducts = totalCostOfProducts;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    @Override
    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    @Override
    public BigDecimal geTotalCostOfProducts() {
        return totalCostOfProducts;
    }

    @Override
    public String toString() {
        return "total=" + total +
                ", totalDiscount=" + totalDiscount +
                ", totalCostOfProducts=" + totalCostOfProducts +
                ", basket=" + basket;
    }
}
