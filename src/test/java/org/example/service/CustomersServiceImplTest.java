package org.example.service;

import org.example.data.model.*;
import org.example.data.repository.CartRepository;
import org.example.data.repository.CustomerRepository;
import org.example.data.repository.OrderRepository;
import org.example.data.repository.UserRepository;
import org.example.dto.request.CartRequest;
import org.example.dto.request.RegisterRequest;
import org.example.dto.response.CartResponse;
import org.example.enums.Role;
import org.example.exception.InvalidCustomerException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomersServiceImplTest {

    @Autowired
   CustomersService customersService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;



    @Test
    public void testForCustomerToUpdateProfile(){
        RegisterRequest CustomerRequest = new RegisterRequest();
        CustomerRequest.setFirstName("Thank");
        CustomerRequest.setLastName("grace");
        CustomerRequest.setEmail("grace12@gmail.com");
        CustomerRequest.setPhoneNumber("09123456789");
        CustomerRequest.setPassword("boladada");
        CustomerRequest.setRole(Role.CUSTOMER);
        CustomerRequest.setEnable(true);
        userService.register(CustomerRequest);

        RegisterRequest updateRequest = new RegisterRequest();
        updateRequest.setEmail("grace12@gmail.com");
        updateRequest.setPhoneNumber("09137217467");
        updateRequest.setFirstName("Daniel");
        customersService.updateProfile(updateRequest);

        User updatedCustomerProfile = userRepository.findByEmail("grace12@gmail.com")
                .orElseThrow(()-> new InvalidCustomerException("customer not found"));

        assertEquals("Daniel", updatedCustomerProfile.getFirstName());
    }

    @Test
    public void testThatCustomerCanAddToCart() {
        CartRequest request = new CartRequest();
        request.setUserId("6866930084012b47d74eaf00");
        request.setProductName("bag");
        request.setProductId("some-product-id");
        request.setPrice(2500);
        request.setDescription("A nice bag with a nice design");
        request.setQuantity(2);

        CartResponse response = customersService.addToCart(request);
        assertEquals(5000, response.getTotalPrice());
    }

    @Test
    public void testThatCustomerCanRemoveFromCart() {
        String userId = "6866930084012b47d74eaf00";
        String productIdToRemove = "prod-1";

        Item item1 = new Item();
        item1.setId(productIdToRemove);
        item1.setProductName("bag");
        item1.setPrice(2500);
        item1.setQuantity(2);

        Item item2 = new Item();
        item2.setId("prod-2");
        item2.setProductName("shoe");
        item2.setPrice(1000);
        item2.setQuantity(1);

        Customer customer = new Customer();
        customer.setId(userId);
        customer.setFirstName("Test");
        customer.setLastName("Micheal");
        customer.setItems(List.of(item1, item2));
        customerRepository.save(customer);

        CartResponse response = customersService.removeFromCart(userId, productIdToRemove);

        assertEquals(1000.0, response.getTotalPrice());
        assertEquals(1, response.getItems().size());
        assertEquals("shoe", response.getItems().getFirst().getProductName());
    }


    @Test
    public void testViewCart_WithItems_ReturnsListOfProductIds() {
        Customer customer = new Customer();
        customer.setId("123");
        customer.setCartId("prod1,prod2,prod3");
        customerRepository.save(customer);

        List<String> result = customersService.viewCart("123");

        assertEquals(3, result.size());
        assertTrue(result.contains("prod1"));
        assertTrue(result.contains("prod2"));
        assertTrue(result.contains("prod3"));
    }

    @Test
    public void testViewCart_EmptyCart_ReturnsEmptyList() {
        Customer customer = new Customer();
        customer.setId("124");
        customer.setCartId("");
        customerRepository.save(customer);

        List<String> result = customersService.viewCart("124");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testViewOrders_ReturnsOrdersForCustomer() {
        Customer customer = new Customer();
        customer.setId("cust123");
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customerRepository.save(customer);

        Item item1 = new Item();
        item1.setId("prod1");
        item1.setProductName("Shoes");
        item1.setPrice(2000);
        item1.setQuantity(1);

        Order order1 = new Order();
        order1.setCustomerId("cust123");
        order1.setItems(List.of(item1));
        order1.setTotalAmount(2000);
        order1.setOrderDate(LocalDateTime.now());

        Item item2 = new Item();
        item2.setId("prod2");
        item2.setProductName("Hat");
        item2.setPrice(1000);
        item2.setQuantity(2);

        Order order2 = new Order();
        order2.setCustomerId("cust123");
        order2.setItems(List.of(item2));
        order2.setTotalAmount(2000);
        order2.setOrderDate(LocalDateTime.now());

        orderRepository.saveAll(List.of(order1, order2));
        List<Order> orders = customersService.viewOrders("cust123");

        assertEquals(2, orders.size());
        assertEquals("cust123", orders.get(0).getCustomerId());
    }

}