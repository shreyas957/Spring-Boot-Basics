package com.sarkar.customer;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDAO{

    // db
    private static List<Customer> customers;

    static {
        customers = new ArrayList<>();
        Customer jamila= new Customer(
                1,
                "Jamila",
                "jamila@gmail.com",
                20
        );
        Customer alex = new Customer(
                2,
                "Alex",
                "alex@gmail.com",
                21
        );
        customers.addAll(java.util.List.of(jamila, alex));

    }
    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existPersonWithEmail(String email) {
        return customers.stream()
                .anyMatch(c -> c.getEmail().equals(email));
    }

    @Override
    public boolean existCustomerById(Integer id) {
        return false;
    }

    @Override
    public void deleteCustomerById(Integer customerId) {

    }

    @Override
    public void updateCustomerById(Customer customer) {

    }

}
