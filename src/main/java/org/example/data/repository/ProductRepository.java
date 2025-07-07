package org.example.data.repository;

import org.example.data.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<Product> findById(String customerId);
    List<Product> findBySellerId(String sellerId);
}
