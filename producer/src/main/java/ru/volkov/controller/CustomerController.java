package ru.volkov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.volkov.service.CustomerService;
import ru.volkov.entity.Customer;
import ru.volkov.dto.CustomerDTO;
import ru.volkov.mapper.CustomerMapper;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public CustomerDTO findOneCustomer(@PathVariable Long id) {
        return CustomerMapper.INSTANCE.toDto(customerService.findOneCustomer(id));
    }

    @GetMapping()
    public List<CustomerDTO> findAllCustomers() {
        return customerService.findAllCustomer().stream().map(CustomerMapper.INSTANCE::toDto).toList();
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer result = CustomerMapper.INSTANCE.toEntity(customerDTO);
        customerService.saveCustomer(result);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
