package ru.volkov.service;

import org.springframework.stereotype.Repository;
import ru.volkov.entity.Customer;

import java.util.List;

@Repository
public interface CustomerService {
    Customer findOneCustomer (Long id);

    List<Customer> findAllCustomer();

    void saveCustomer(Customer customer);

    void updateCustomer(Long id, Customer customer);

    void deleteCustomer(Long id);
}
