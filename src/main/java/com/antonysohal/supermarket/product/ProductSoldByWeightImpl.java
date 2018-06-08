package com.antonysohal.supermarket.product;

import java.math.BigDecimal;
import java.util.Objects;

class ProductSoldByWeightImpl extends AbstractProductImpl implements ProductSoldByWeight {

    WeightUnit weightUnit = null;

    ProductSoldByWeightImpl(String name, BigDecimal price, WeightUnit weightUnit) {
        super(name, price);
        setWeightUnit(weightUnit);
    }

    public void setWeightUnit(WeightUnit weightUnit) {
        this.weightUnit = weightUnit;
    }

    @Override
    public WeightUnit getWeightUnit() {
        return weightUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductSoldByWeightImpl that = (ProductSoldByWeightImpl) o;
        return weightUnit == that.weightUnit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), weightUnit);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", weightUnit=" + weightUnit;
    }
}
