package com.antonysohal.supermarket.product;

/**
 * Define a weight unit.
 *
 * @todo add function to convert from one unit to another
 */
public enum WeightUnit {

    KILOGRAM("kg");

    private String unitAbbreviation;

    WeightUnit(String unitAbbreviation) {
        this.unitAbbreviation = unitAbbreviation;
    }
}
