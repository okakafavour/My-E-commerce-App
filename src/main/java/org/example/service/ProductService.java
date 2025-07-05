package org.example.service;


import org.example.data.model.Product;
import org.example.dto.request.ProductRequest;

import java.util.List;

public interface ProductService {

    Product createProduct(ProductRequest request);

    Product getProductById(String productId);

    List<Product> getAllProducts();

    Product updateProduct(String productId, ProductRequest request);

    void deleteProductById(String productId);

    List<Product> searchProducts(String keyword);

    List<Product> getProductsByCategory(String category);

    void changeProductStatus(String productId, boolean available);
}
