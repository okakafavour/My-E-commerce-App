package org.example.util;

import org.example.data.model.Admin;
import org.example.data.model.Customer;
import org.example.data.model.Seller;
import org.example.data.model.User;
import org.example.enums.Role;
import org.springframework.stereotype.Component;

@Component
    public class UserMapper {

        public Customer mapToCustomer(User user) {
            Customer customer = new Customer();
            customer.setId(user.getId());
            customer.setFirstName(user.getFirstName());
            customer.setLastName(user.getLastName());
            customer.setEmail(user.getEmail());
            customer.setPhoneNumber(user.getPhoneNumber());
            customer.setPassword(user.getPassword());
            customer.setRole(Role.CUSTOMER);
            return customer;
        }

        public Seller mapToSeller(User user) {
            Seller seller = new Seller();
            seller.setId(user.getId());
            seller.setFirstName(user.getFirstName());
            seller.setLastName(user.getLastName());
            seller.setEmail(user.getEmail());
            seller.setPhoneNumber(user.getPhoneNumber());
            seller.setPassword(user.getPassword());
            seller.setRole(Role.VENDOR);
            return seller;
        }

        public Admin mapToAdmin(User user) {
            Admin admin = new Admin();
            admin.setId(user.getId());
            admin.setFirstName(user.getFirstName());
            admin.setLastName(user.getLastName());
            admin.setEmail(user.getEmail());
            admin.setPhoneNumber(user.getPhoneNumber());
            admin.setPassword(user.getPassword());
            admin.setRole(Role.ADMIN);
            return admin;
        }
    }


