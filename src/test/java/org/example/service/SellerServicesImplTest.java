package org.example.service;

import org.example.data.model.Product;
import org.example.data.model.Seller;
import org.example.data.repository.ProductRepository;
import org.example.data.repository.UserRepository;
import org.example.dto.request.ProductRequest;
import org.example.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SellerServicesImplTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SellerServicesImpl sellerServices;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void setUp(){
        userRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void testToAddAProduct(){

        Seller seller = new Seller();
        seller.setId("seller123");
        seller.setFirstName("John");
        seller.setLastName("Doe");
        seller.setEmail("john@example.com");
        seller.setPassword("password");
        seller.setPhoneNumber("1234567890");
        seller.setRole(Role.VENDOR);
        userRepository.save(seller);


        ProductRequest  productRequest = new ProductRequest();
        productRequest.setName("Phone");
        productRequest.setCategory("Electronics");
        productRequest.setPrice(299.99);
        productRequest.setQuantity(5);
        productRequest.setDescription("Smartphone");
        productRequest.setImageUrl("https://www.freepik.com/free-photo/elegant-smartphone-composition_27375319.htm#fromView=search&page=1&position=14&uuid=a6431bed-80c9-4c18-830b-0e1102344795&query=phone");

        sellerServices.addProduct(productRequest, seller.getId());

        List<Product> allProducts = productRepository.findAll();
        assertEquals(1, allProducts.size());
        assertEquals("Phone", allProducts.get(0).getProductName());
    }

    @Test
    public void testToRemoveAProduct(){

        Seller seller = new Seller();
        seller.setId("seller123");
        seller.setFirstName("John");
        seller.setLastName("Doe");
        seller.setEmail("john@example.com");
        seller.setPassword("password");
        seller.setPhoneNumber("1234567890");
        seller.setRole(Role.VENDOR);
        userRepository.save(seller);


        ProductRequest  productRequest = new ProductRequest();
        productRequest.setName("Phone");
        productRequest.setCategory("Electronics");
        productRequest.setPrice(299.99);
        productRequest.setQuantity(5);
        productRequest.setDescription("Smartphone");
        productRequest.setImageUrl("https://www.freepik.com/free-photo/elegant-smartphone-composition_27375319.htm#fromView=search&page=1&position=14&uuid=a6431bed-80c9-4c18-830b-0e1102344795&query=phone");
        sellerServices.addProduct(productRequest, seller.getId());

        sellerServices.removeProduct("Phone", seller.getId());
        List<Product> allProducts = productRepository.findAll();
        assertEquals(0, allProducts.size());
    }
}

