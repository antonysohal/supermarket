package com.antonysohal.supermarket;

import com.antonysohal.supermarket.basket.Basket;
import com.antonysohal.supermarket.basket.BasketService;
import com.antonysohal.supermarket.basket.BasketServiceImpl;
import com.antonysohal.supermarket.discount.Discount;
import com.antonysohal.supermarket.discount.DiscountService;
import com.antonysohal.supermarket.discount.DiscountServiceImpl;
import com.antonysohal.supermarket.product.Product;
import com.antonysohal.supermarket.product.ProductService;
import com.antonysohal.supermarket.product.ProductServiceImpl;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class StepsDefs implements En {

    BasketService basketService = BasketServiceImpl.getInstance();

    Basket basket;

    ProductService productService = ProductServiceImpl.getInstance();

    DiscountService discountService = DiscountServiceImpl.getInstace();

    CheckoutService checkoutService;

    Receipt receipt;

    public StepsDefs() {

        Given("^I have shopping basket$", () -> {
            basket = basketService.createBasket();
            assertThat(basket).isNotNull();
        });

        Given("the following products exist:", (DataTable dataTable) -> {
            dataTable.asMaps(String.class, String.class).forEach(
                    row -> {
                        Optional<Product> productOptional = productService.createProduct(row.get("name").toString(),
                                new BigDecimal(row.get("price").toString()), null);

                        // Test product created
                        assertThat(productOptional.isPresent()).isTrue();
                        assertThat(productOptional.get())
                                .isNotNull()
                                .hasFieldOrPropertyWithValue("name", row.get("name"))
                                .hasFieldOrPropertyWithValue("price", new BigDecimal(row.get("price").toString()));
                    }
            );
        });

        Given("the following discounts exist:", (DataTable dataTable) -> {
            dataTable.asMaps(String.class, String.class).forEach(
                    row -> {

                        Optional<Product> productOptional = productService.getProduct(row.get("product").toString());
                        assertThat(productOptional.isPresent()).isTrue();

                        Optional<Discount> discountOptional = discountService.createDiscount(
                                row.get("name").toString(),
                                new BigDecimal(row.get("discount").toString()),
                                productOptional.get(),
                                Integer.parseInt(row.get("qty").toString()));

                        assertThat(discountOptional.isPresent()).isTrue();
                        assertThat(discountOptional.get())
                                .isNotNull()
                                .hasFieldOrPropertyWithValue("name", row.get("name").toString())
                                .hasFieldOrPropertyWithValue("value", new BigDecimal(row.get("discount").toString()))
                                .hasFieldOrPropertyWithValue("product", productOptional.get())
                                .hasFieldOrPropertyWithValue("quantity", Integer.parseInt(row.get("qty").toString()));
                    }
            );
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
