package ru.volkov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.volkov.entity.Customer;
import ru.volkov.exception.NotFoundException;
import ru.volkov.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl (CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findOneCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Long id, Customer customer) {
        if (customerRepository.existsById(id)) {
            customer.setId(id);
            customerRepository.save(customer);
        } else {
            throw new IllegalArgumentException("Atm with id " + id + " does not exist");
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
