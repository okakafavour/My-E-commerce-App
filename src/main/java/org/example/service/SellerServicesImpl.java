package org.example.service;

import org.example.data.model.Order;
import org.example.data.model.Product;
import org.example.data.model.User;
import org.example.data.repository.OrderRepository;
import org.example.data.repository.ProductRepository;
import org.example.data.repository.UserRepository;
import org.example.dto.request.ProductRequest;
import org.example.enums.Role;
import org.example.exception.InvalidCustomerException;
import org.example.exception.ProductNotFoundException;
import org.example.exception.UnauthorizedException;
import org.example.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServicesImpl implements SellerServices{

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;


    @Override
    public void addProduct(ProductRequest productRequest, String sellerId) {
        User seller = userRepository.findById(sellerId)
                .orElseThrow(()-> new UserNotFoundException("Seller not found"));

        if(!seller.getRole().equals(Role.VENDOR)) throw new UnauthorizedException("User is not authorize to add products");

        Product product = new Product();
        product.setProductName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        product.setDescription(productRequest.getDescription());
        product.setImageUrl(productRequest.getImageUrl());
        product.setAvailable(true);
        product.setSellerId(sellerId);
        productRepository.save(product);
    }

    @Override
    public void removeProduct(String productName, String sellerId) {
        Product product = productRepository.findByProductNameAndSellerId(productName, sellerId)
                .orElseThrow(()-> new ProductNotFoundException("product not found"));
        productRepository.delete(product);
    }

    @Override
    public Product updateProduct(String productId, ProductRequest productRequest) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new InvalidCustomerException("product not found"));

        product.setProductName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setImageUrl(productRequest.getImageUrl());
        product.setQuantity(productRequest.getQuantity());
        product.setAvailable(true);
        return productRepository.save(product);
    }

    @Override
    public List<Product> viewMyProducts(String sellerId) {
        return productRepository.findBySellerId(sellerId);
    }


    @Override
    public void changeProductAvailability(String productId, boolean available) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        boolean isSold = orderRepository.existsByProductId(productId);
        if (isSold) throw new RuntimeException("product has already been sold.");

        product.setAvailable(available);
        productRepository.save(product);
    }

}
