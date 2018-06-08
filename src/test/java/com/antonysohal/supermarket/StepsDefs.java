package com.antonysohal.supermarket;

import com.antonysohal.supermarket.basket.Basket;
import com.antonysohal.supermarket.discount.DiscountService;
import com.antonysohal.supermarket.product.ProductService;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

import static org.assertj.core.api.Assertions.assertThat;

public class StepsDefs implements En {

    Basket basket;

    ProductService productService;

    DiscountService discountService;

    CheckoutService checkoutService;

    Receipt receipt;


    public StepsDefs() {

        Given("^I have shopping basket$", () -> {
            basket = null;
        });

        Given("the following products exist:", (DataTable dataTable) -> {

        });

        Given("the following discounts exist:", (DataTable dataTable) -> {

        });

        Given("I add the following to my basket:", (DataTable dataTable) -> {

        });

        When("^I checkout$", () -> {
            receipt = checkoutService.checkout(basket);
        });

        Then("my total discount should be {double}", (Double expectedDiscount) -> {
            assertThat(receipt)
                    .isNotNull()
                    .hasFieldOrPropertyWithValue("totalDiscount", expectedDiscount);
        });

        Then("my total should be {double}", (Double expectedTotal) -> {
            assertThat(receipt)
                    .hasFieldOrPropertyWithValue("total", expectedTotal);
        });
    }

}
