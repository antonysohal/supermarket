package com.antonysohal.supermarket.discount;

import com.antonysohal.supermarket.product.Product;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductQuantityDiscountImpl implements ProductQuantityDiscount {

    protected BigDecimal value;
    protected Product product;
    protected Integer quantity;
    protected String name;


    ProductQuantityDiscountImpl(String name, Product product, Integer quantity, BigDecimal value) {
        setName(name);
        setProduct(product);
        setQuantity(quantity);
        setValue(value);
    }


    public void setValue(BigDecimal value) {
        if (value.compareTo(BigDecimal.ZERO) > -1) {
            throw new IllegalArgumentException("Discount price must be less than 0");
        }
        this.value = value;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductQuantityDiscountImpl that = (ProductQuantityDiscountImpl) o;
        return Objects.equals(value, that.value) &&
                Objects.equals(product, that.product) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(value, product, quantity, name);
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", value=" + value +
                ", product=" + product +
                ", quantity=" + quantity;

    }
}
