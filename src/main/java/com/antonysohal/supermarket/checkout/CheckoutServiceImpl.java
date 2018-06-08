package com.antonysohal.supermarket.checkout;

import com.antonysohal.supermarket.basket.Basket;
import com.antonysohal.supermarket.discount.Discount;
import com.antonysohal.supermarket.discount.DiscountService;
import com.antonysohal.supermarket.discount.DiscountServiceImpl;
import com.antonysohal.supermarket.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CheckoutServiceImpl implements CheckoutService {

    private DiscountService discountService = DiscountServiceImpl.getInstance();

    private static CheckoutServiceImpl INSTANCE;


    public static CheckoutService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CheckoutServiceImpl();
        }
        return INSTANCE;
    }

    @Override
    public Receipt checkout(Basket basket) {
        List<Discount> applicableDicounts = discountService.getDiscounts(basket.getContents().stream().distinct().collect(Collectors.toList()));
        Map<Product, Integer> productsWithQuantity = basket.getContents().stream().collect(Collectors.groupingBy(product -> product, Collectors.reducing(0, e -> 1, Integer::sum)));

        List<Discount> discountApplied = new ArrayList<>();

        Optional<Discount> discountOptional = applyDiscounts(applicableDicounts, productsWithQuantity).stream().findFirst();

        /**
         * Go through each applicable discount
         */
        while (discountOptional.isPresent()) {
            discountApplied.add(discountOptional.get());
            discountOptional.get().getCriteria()
                    .forEach((product, discountQuantity) -> {
                                /**
                                 * remove products where discount has been applied.
                                 */
                                productsWithQuantity.computeIfPresent(product, (product1, basketQuantity) -> {
                                    return basketQuantity - discountQuantity;
                                });
                            }
                    );
            discountOptional = applyDiscounts(applicableDicounts, productsWithQuantity).stream().findFirst();
        }

        return new ReceiptImpl(basket, discountApplied);
    }

    /**
     * Return a list of discount which are valid
     *
     * @param discounts            the discounts
     * @param productsWithQuantity the product with quantity
     * @return list of discounts
     */
    List<Discount> applyDiscounts(List<Discount> discounts, Map<Product, Integer> productsWithQuantity) {
        return discounts.stream()
                .filter(discount -> discount.canApply(productsWithQuantity))
                .collect(Collectors.toList());
    }

}
