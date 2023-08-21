package com.sarkar.customer;

import com.sarkar.exception.DuplicateResourceException;
import com.sarkar.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerService(@Qualifier("jpa") CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.selectAllCustomers();
    }

    public Customer getCustomer(Integer id) {
        return customerDAO.selectCustomerById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Customer with id [%s] does not exist".formatted(id)
                        )
                );
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        // Check if email exist o.w add
        if (customerDAO.existPersonWithEmail(customerRegistrationRequest.email())) {
            throw new DuplicateResourceException("Customer with email already exist");
        }

        Customer customer = new Customer(
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                customerRegistrationRequest.age()
        );
        customerDAO.insertCustomer (customer);
    }

    public void deleteCustomerById(Integer customerId) {
        if(!customerDAO.existCustomerById(customerId)) {
            throw new ResourceNotFoundException("Customer not found");
        }
        customerDAO.deleteCustomerById(customerId);

    }

    public void updateCustomerById(Integer customerId, CustomerUpdateRequest customerUpdateRequest) {
        if(!customerDAO.existCustomerById(customerId)) {
            throw new ResourceNotFoundException("Customer with id [%s] does not exist".formatted(customerId));
        }
        Customer customer = getCustomer(customerId);
        customer.setName(customerUpdateRequest.name());
        customer.setEmail(customerUpdateRequest.email());
        customer.setAge(customerUpdateRequest.age());
        customerDAO.updateCustomerById(customer);
    }
}
