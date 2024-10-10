package ru.volkov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.volkov.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
