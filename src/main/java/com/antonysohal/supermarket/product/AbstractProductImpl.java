package com.antonysohal.supermarket.product;

import java.math.BigDecimal;
import java.util.Objects;

class AbstractProductImpl implements Product {

    protected String name;
    protected BigDecimal price;

    AbstractProductImpl() {
    }

    AbstractProductImpl(String name, BigDecimal price) {
        setName(name);
        setPrice(price);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractProductImpl that = (AbstractProductImpl) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", price=" + price;
    }
}
