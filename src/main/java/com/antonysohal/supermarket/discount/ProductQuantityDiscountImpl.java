package com.antonysohal.supermarket.discount;

import com.antonysohal.supermarket.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class ProductQuantityDiscountImpl implements Discount {

    protected BigDecimal value;
    protected String name;
    protected Map<Product, Integer> criteria;


    ProductQuantityDiscountImpl(String name, Map<Product, Integer> criteria, BigDecimal value) {
        setName(name);
        setValue(value);
        setCriteria(criteria);
    }


    public void setValue(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) > -1) {
            throw new IllegalArgumentException("Discount price must be less than 0");
        }
        this.value = value;
    }

    @Override
    public boolean canApply(Map<Product, Integer> productWithQuantity) {
        return productWithQuantity.keySet().containsAll(getProducts()) &&
                productWithQuantity.entrySet().stream().allMatch(e -> {
                    Integer passedQuantity = e.getValue();
                    //provide default value of 0 as the product may not exist in criteria
                    Integer criteriaQuantity = criteria.getOrDefault(e.getKey(), 0);
                    return (criteriaQuantity.compareTo(passedQuantity) <= 0);
                });
    }

    @Override
    public Map<Product, Integer> getCriteria() {
        return criteria;
    }

    public void setCriteria(Map<Product, Integer> criteria) {
        this.criteria = criteria;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Product> getProducts() {
        return Collections.unmodifiableList(new ArrayList<>(criteria.keySet()));
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductQuantityDiscountImpl that = (ProductQuantityDiscountImpl) o;
        return Objects.equals(value, that.value) &&
                Objects.equals(name, that.name) &&
                Objects.equals(criteria, that.criteria);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value, name, criteria);
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", value=" + value +
                ", criteria=" + criteria;

    }
}
