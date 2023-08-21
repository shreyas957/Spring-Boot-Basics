package com.sarkar.customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    List<Customer> selectAllCustomers();
    Optional<Customer> selectCustomerById(Integer id);
    void insertCustomer(Customer customer);
    boolean existPersonWithEmail(String email);
    boolean existCustomerById(Integer id);
    void deleteCustomerById(Integer customerId);
    void updateCustomerById(Customer customer);
}
