package org.example.service;

import org.example.data.model.*;
import org.example.data.repository.AdminRepository;
import org.example.data.repository.OrderRepository;
import org.example.data.repository.ProductRepository;
import org.example.data.repository.UserRepository;
import org.example.dto.request.AdminLoginRequest;
import org.example.dto.request.AdminRegisterRequest;
import org.example.dto.response.AdminLoginResponse;
import org.example.dto.response.AdminRegisterResponse;
import org.example.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceImplTest {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminServiceImpl adminService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @BeforeEach
    void setUP(){
        adminRepository.deleteAll();
        userRepository.deleteAll();
        orderRepository.deleteAll();
        productRepository.deleteAll();
    }


    @Test
    public void testToRegisterAdminToTheDataBase(){
        AdminRegisterRequest request = new AdminRegisterRequest();
        request.setFirstName("Admin");
        request.setLastName("User");
        request.setEmail("admin12@gmail.com");
        request.setPassword("admin123");
        request.setPhoneNumber("08012345678");

        AdminRegisterResponse response = adminService.register(request);

        Admin savedAdmin = adminRepository.findByEmail("admin12@gmail.com");

        assertNotNull(savedAdmin);
        assertNotEquals("admin123", savedAdmin.getPassword());
        assertTrue(savedAdmin.getPassword().startsWith("$2a$"));
        assertEquals("Admin registered successfully", response.getMessage());
    }


    @Test
    public void testToLoginAsAdmin(){

        AdminRegisterRequest request = new AdminRegisterRequest();
        request.setFirstName("Admin");
        request.setLastName("User");
        request.setEmail("admin12@gmail.com");
        request.setPassword("admin123");
        request.setPhoneNumber("08012345678");
        adminService.register(request);

        AdminLoginRequest adminLoginRequest = new AdminLoginRequest();
        adminLoginRequest.setEmail("admin12@gmail.com");
        adminLoginRequest.setPassword("admin123");
        AdminLoginResponse response =  adminService.login(adminLoginRequest);
        assertEquals("Login Successfully", response.getMessage());
    }

    @Test
    public void testThatAdminCanViewTheListOfUser(){
        AdminRegisterRequest request = new AdminRegisterRequest();
        request.setFirstName("SecondAdmin");
        request.setLastName("jide");
        request.setEmail("jide12@gmail.com");
        request.setPassword("jide123");
        request.setEnable(true);
        request.setPhoneNumber("08112345678");
        AdminRegisterResponse response = adminService.register(request);
        assertEquals("Admin registered successfully", response.getMessage());

        AdminLoginRequest loginRequest = new AdminLoginRequest();
        loginRequest.setEmail("jide12@gmail.com");
        loginRequest.setPassword("jide123");

        AdminLoginResponse loginResponse = adminService.login(loginRequest);
        assertEquals("Login Successfully", loginResponse.getMessage());

        Customer user1 = new Customer();
        user1.setEmail("user1@gmail.com");
        user1.setPassword("password1");
        user1.setFirstName("User ");
        user1.setLastName("One");
        user1.setRole(Role.CUSTOMER);
        userRepository.save(user1);

        Customer user2 = new Customer();
        user2.setEmail("user2@gmail.com");
        user2.setPassword("password2");
        user2.setFirstName("User");
        user2.setRole(Role.CUSTOMER);
        user2.setLastName("Two");
        userRepository.save(user2);

        Seller user3 = new Seller();
        user3.setEmail("user13@gmail.com");
        user3.setPassword("password2");
        user3.setFirstName("User");
        user3.setRole(Role.VENDOR);
        user3.setLastName("Three");
        userRepository.save(user3);

        Seller user4 = new Seller();
        user4.setFirstName("User");
        user4.setLastName("Four");
        user4.setEmail("user412@gmail.com");
        user4.setRole(Role.VENDOR);
        userRepository.save(user4);

        List<User> users = adminService.getAllUsers("jide12@gmail.com");
        assertEquals(4, users.size());
    }

    @Test
    public void testThatAdminCanDeleteAUser(){
        AdminRegisterRequest request = new AdminRegisterRequest();
        request.setFirstName("ThirdAdmin");
        request.setLastName("jide");
        request.setEmail("sam12@gmail.com");
        request.setPassword("sam123");
        request.setEnable(true);
        request.setPhoneNumber("08112345678");
        AdminRegisterResponse response = adminService.register(request);
        assertEquals("Admin registered successfully", response.getMessage());

        AdminLoginRequest loginRequest = new AdminLoginRequest();
        loginRequest.setEmail("sam12@gmail.com");
        loginRequest.setPassword("sam123");

        AdminLoginResponse loginResponse = adminService.login(loginRequest);
        assertEquals("Login Successfully", loginResponse.getMessage());

        Customer user1 = new Customer();
        user1.setEmail("user1@gmail.com");
        user1.setPassword("password1");
        user1.setFirstName("User ");
        user1.setLastName("One");
        user1.setRole(Role.CUSTOMER);
        userRepository.save(user1);

        Customer user2 = new Customer();
        user2.setEmail("user2@gmail.com");
        user2.setPassword("password2");
        user2.setFirstName("User");
        user2.setRole(Role.CUSTOMER);
        user2.setLastName("Two");
        userRepository.save(user2);

        adminService.deleteUserByEmail("user2@gmail.com");
        assertEquals(1, userRepository.count());
    }

    @Test
    public void testThatUserCanBeDeletedById() {
        Customer user = new Customer();
        user.setFirstName("Blessing");
        user.setLastName("debora");
        user.setEmail("john@gmail.com");
        user.setPassword("john123");
        userRepository.save(user);

        assertEquals(1, userRepository.count());
        adminService.deleteUserById(user.getId());
        assertEquals(0, userRepository.count());
    }

    @Test
    public void testViewAllProducts() {
        Product p1 = new Product();
        p1.setId("1");
        p1.setProductName("Laptop");
        p1.setCategory("Electronics");
        p1.setQuantity(10);
        p1.setDescription("Gaming Laptop");
        p1.setPrice(2000.0);
        p1.setImageUrl("https://example.com/laptop.jpg");
        p1.setSellerId("seller123");
        productRepository.save(p1);

        Product p2 = new Product();
        p2.setId("2");
        p2.setProductName("Phone");
        p2.setCategory("Electronics");
        p2.setQuantity(20);
        p2.setDescription("Smartphone");
        p2.setPrice(1000.0);
            p2.setImageUrl("data:image/jpeg;base64,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");
        p2.setSellerId("seller456");
        productRepository.save(p2);

        List<Product> products = adminService.viewAllProducts();

        assertEquals(2, products.size());
        assertTrue(products.stream().anyMatch(p -> p.getProductName().equals("Laptop")));
        assertTrue(products.stream().anyMatch(p -> p.getProductName().equals("Phone")));
    }

    @Test
    public void testViewAllOrders() {

        Item item1 = new Item();
        item1.setId("prod1");
        item1.setProductName("Shoes");
        item1.setPrice(2000);
        item1.setQuantity(1);

        Item item2 = new Item();
        item1.setId("prod1");
        item1.setProductName("clothes");
        item1.setPrice(2000);
        item1.setQuantity(2);

        Order order1 = new Order();
        order1.setId("1");
        order1.setCustomerId("cust123");
        order1.setItems(List.of(item1));
        order1.setTotalAmount(2000.0);
        order1.setOrderDate(LocalDateTime.now());

        Order order2 = new Order();
        order2.setId("2");
        order2.setCustomerId("cust123");
        order2.setItems(List.of(item2));
        order2.setTotalAmount(4000.0);
        order2.setOrderDate(LocalDateTime.now());

        orderRepository.save(order1);
        orderRepository.save(order2);

        List<Order> orders = adminService.viewAllOrders();

        assertEquals(2, orders.size());
    }

    @Test
    public void testThatProductCanBeDeletedById() {
        Product p1 = new Product();
        p1.setId("1");
        p1.setProductName("Laptop");
        p1.setCategory("Electronics");
        p1.setQuantity(10);
        p1.setDescription("Gaming Laptop");
        p1.setPrice(2000.0);
        p1.setImageUrl("https://example.com/laptop.jpg");
        p1.setSellerId("seller123");
        productRepository.save(p1);

        assertEquals(1, productRepository.count());

        adminService.deleteProductById("1");
        assertEquals(0, productRepository.count());
    }

}