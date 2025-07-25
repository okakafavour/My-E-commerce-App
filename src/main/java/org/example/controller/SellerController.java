package org.example.controller;

import org.example.data.model.Order;
import org.example.data.model.Product;
import org.example.data.repository.OrderRepository;
import org.example.data.repository.ProductRepository;
import org.example.dto.request.ProductRequest;
import org.example.service.SellerServicesImpl;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seller")
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('VENDOR')")
    public class SellerController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserService userService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    SellerServicesImpl sellerServices;



    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        Product addedProduct = productRepository.save(product);
            return ResponseEntity.ok(addedProduct);
    }

    @GetMapping("/orders")
    public ResponseEntity<?> viewOrders() {
        List<Order> savedOrders = orderRepository.findAll();
        return ResponseEntity.ok(savedOrders);
    }

    @GetMapping("/view-sellers-products/{sellerId}")
    public ResponseEntity<?> viewMyProducts(@PathVariable String sellerId){
        List<Product> products = sellerServices.viewMyProducts(sellerId);
        return ResponseEntity.ok(products);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable String productId, ProductRequest productRequest){
        Product updatedProduct = sellerServices.updateProduct(productId, productRequest);
        return ResponseEntity.ok(updatedProduct);
    }
}




