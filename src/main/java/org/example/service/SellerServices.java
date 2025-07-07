package org.example.service;

import org.example.data.model.Order;
import org.example.data.model.Product;
import org.example.dto.request.LoginRequest;
import org.example.dto.request.ProductRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.LoginResponse;
import org.example.dto.response.RegisterResponse;

import java.util.List;

public interface SellerServices {
    void addProduct(ProductRequest productRequest, String sellerId);
    void removeProduct(String productId);
    Product updateProduct(String productId, ProductRequest productRequest);
    List<Product> viewMyProducts(String sellerId);
    void changeProductAvailability(String productId, boolean available);

}
