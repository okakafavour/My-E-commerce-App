package org.example.service;

import org.example.data.model.Product;
import org.example.dto.request.ProductRequest;

import java.util.List;

public class ProductServiceImpl implements ProductService{
    @Override
    public Product createProduct(ProductRequest request) {
        return null;
    }

    @Override
    public Product getProductById(String productId) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product updateProduct(String productId, ProductRequest request) {
        return null;
    }

    @Override
    public void deleteProductById(String productId) {

    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return List.of();
    }

    @Override
    public void changeProductStatus(String productId, boolean available) {

    }
}
