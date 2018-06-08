package com.antonysohal.supermarket.product;

import java.util.List;

/**
 * Service interface for products
 */
public interface ProductService {

    List<Product> getProducts();

    Product addProduct(Product product);

    Product getProduct(String name);
}
