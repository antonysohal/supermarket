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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Steps for Checkout Scenario
 */
public class CheckoutStepsDefs implements En {

    BasketService basketService = BasketServiceImpl.getInstance();

    Basket basket;

    ProductService productService = ProductServiceImpl.getInstance();

    DiscountService discountService = DiscountServiceImpl.getInstance();

    CheckoutService checkoutService = CheckoutServiceImpl.getInstance();

    Receipt receipt;

    public CheckoutStepsDefs() {

        Given("^I have shopping basket$", () -> {
            basket = basketService.createBasket();
            assertThat(basket).isNotNull();
        });

        Given("the following products exist:", (DataTable dataTable) -> {
            dataTable.asMaps(String.class, String.class).forEach(
                    row -> {
                        // Create product from Datatable
                        Optional<Product> productOptional = productService.createProduct(row.get("name").toString(),
                                new BigDecimal(row.get("price").toString()), null);

                        // Test the created products
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
                        // Create the discount from Datatable
                        List<String> productNames = Arrays.asList(row.get("product").toString().split("\\s*,\\s*"));
                        Map<Product, Integer> criteria = new HashMap<>();
                        productNames.forEach(productName -> {
                            Optional<Product> productOptional = productService.getProduct(productName);
                            assertThat(productOptional.isPresent()).as("Product exists: " + productName).isTrue();
                            criteria.put(productOptional.get(), Integer.parseInt(row.get("qty").toString()));
                        });

                        Optional<Discount> discountOptional = discountService.createDiscount(
                                row.get("name").toString(),
                                new BigDecimal(row.get("discount").toString()),
                                criteria);

                        // Test the discount created
                        assertThat(discountOptional.isPresent()).isTrue();
                        assertThat(discountOptional.get())
                                .isNotNull()
                                .hasFieldOrPropertyWithValue("name", row.get("name").toString())
                                .hasFieldOrPropertyWithValue("value", new BigDecimal(row.get("discount").toString()))
                                .hasFieldOrPropertyWithValue("criteria", criteria);
                    });
        });

        Given("I add the following to my basket:", (DataTable dataTable) -> {
            dataTable.asMaps(String.class, String.class).forEach(
                    row -> {
                        // Add product to basket from Datatable
                        String productName = row.get("name").toString();
                        Integer quantity = Integer.parseInt(row.get("qty").toString());

                        Optional<Product> productOptional = productService.getProduct(productName);

                        assertThat(productOptional.isPresent()).isTrue();
                        assertThat(productOptional.get())
                                .isNotNull()
                                .hasFieldOrPropertyWithValue("name", productName);

                        assertThat(basket.add(productOptional.get(), quantity))
                                .isNotNull();

                        // Test the product are added to basket
                        assertThat(basket.getContents())
                                .filteredOn("name", productName)
                                .size().isEqualTo(quantity);
                    });
        });

        When("^I checkout$", () -> {
            // Checkout
            receipt = checkoutService.checkout(basket);
        });

        Then("my total discount should be {double}", (Double expectedDiscount) -> {
            // Test the output of the checkout is valid
            assertThat(receipt)
                    .isNotNull()
                    .hasFieldOrPropertyWithValue("totalDiscount", BigDecimal.valueOf(expectedDiscount));
        });

        Then("my total should be {double}", (Double expectedTotal) -> {
            // Test the output of the checkout is valid
            assertThat(receipt)
                    .hasFieldOrPropertyWithValue("total", BigDecimal.valueOf(expectedTotal));
        });
    }

}
