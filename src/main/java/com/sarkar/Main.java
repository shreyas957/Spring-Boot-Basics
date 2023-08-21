package com.sarkar;

import com.sarkar.customer.Customer;
import com.sarkar.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Main {

//    CustomerController talks to CustomerService --> CustomerDAO
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);

//        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
//        for (String beanDefinitionName : beanDefinitionNames) {
//            System.out.println(beanDefinitionName);
//        }
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository) {
        return args -> {
            Customer jamila= new Customer(
                    "Jamila",
                    "jamila@gmail.com",
                    20
            );
            Customer alex = new Customer(
                    "Alex",
                    "alex@gmail.com",
                    21
            );

            List<Customer> customers = List.of(alex, jamila);
            customerRepository.saveAll(customers);
        };
    }
}
