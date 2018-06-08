package com.antonysohal.supermarket;

import com.antonysohal.supermarket.basket.Basket;
import com.antonysohal.supermarket.basket.BasketService;
import com.antonysohal.supermarket.basket.BasketServiceImpl;
import com.antonysohal.supermarket.checkout.CheckoutService;
import com.antonysohal.supermarket.checkout.CheckoutServiceImpl;
import com.antonysohal.supermarket.checkout.Receipt;
import com.antonysohal.supermarket.discount.Discount;
import com.antonysohal.supermarket.discount.DiscountService;
import com.antonysohal.supermarket.discount.DiscountServiceImpl;
import com.antonysohal.supermarket.product.Product;
import com.antonysohal.supermarket.product.ProductService;
import com.antonysohal.supermarket.product.ProductServiceImpl;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class StepsDefs implements En {

    BasketService basketService = BasketServiceImpl.getInstance();

    Basket basket;

    ProductService productService = ProductServiceImpl.getInstance();

    DiscountService discountService = DiscountServiceImpl.getInstance();

    CheckoutService checkoutService = CheckoutServiceImpl.getInstance();

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

                        Map<Product, Integer> expectedCriteria = new HashMap<>();
                        expectedCriteria.put(productOptional.get(), Integer.parseInt(row.get("qty").toString()));

                        assertThat(discountOptional.isPresent()).isTrue();
                        assertThat(discountOptional.get())
                                .isNotNull()
                                .hasFieldOrPropertyWithValue("name", row.get("name").toString())
                                .hasFieldOrPropertyWithValue("value", new BigDecimal(row.get("discount").toString()))
                                .hasFieldOrPropertyWithValue("criteria", expectedCriteria);
                    }
            );
        });

        Given("I add the following to my basket:", (DataTable dataTable) -> {
            dataTable.asMaps(String.class, String.class).forEach(
                    row -> {
                        String productName = row.get("name").toString();
                        Integer quantity = Integer.parseInt(row.get("qty").toString());

                        Optional<Product> productOptional = productService.getProduct(productName);

                        assertThat(productOptional.isPresent()).isTrue();
                        assertThat(productOptional.get())
                                .isNotNull()
                                .hasFieldOrPropertyWithValue("name", productName);

                        assertThat(basket.add(productOptional.get(), quantity))
                                .isNotNull();

                        assertThat(basket.getContents())
                                .filteredOn("name", productName)
                                .size().isEqualTo(quantity);
                    }
            );
        });

        When("^I checkout$", () -> {
            receipt = checkoutService.checkout(basket);
        });

        Then("my total discount should be {double}", (Double expectedDiscount) -> {
            assertThat(receipt)
                    .isNotNull()
                    .hasFieldOrPropertyWithValue("totalDiscount", BigDecimal.valueOf(expectedDiscount));
        });

        Then("my total should be {double}", (Double expectedTotal) -> {
            assertThat(receipt)
                    .hasFieldOrPropertyWithValue("total", BigDecimal.valueOf(expectedTotal));
        });
    }

}
