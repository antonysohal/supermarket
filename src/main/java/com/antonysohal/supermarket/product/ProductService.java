package com.antonysohal.supermarket.product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for products
 */
public interface ProductService {

    /**
     * Return all product sold from inventory
     *
     * @return list of products
     */
    List<Product> getProducts();

    /**
     * Get product by name
     *
     * @param name name of product
     * @return a product or <tt>Optional.EMPTY</tt> if product does exist
     */
    Optional<Product> getProduct(String name);

    /**
     * Create and add product to inventory
     *
     * @param name       name of product, must be unqiue
     * @param price      the price of product
     * @param weightUnit can be <tt>null</tt>if product is not sold by weight
     * @return newly created product or  <tt>Optional.EMPTY</tt> if product with this name exists
     */
    Optional<Product> createProduct(String name, BigDecimal price, WeightUnit weightUnit);
}
