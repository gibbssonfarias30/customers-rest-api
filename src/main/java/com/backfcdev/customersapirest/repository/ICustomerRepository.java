package com.backfcdev.customersapirest.repository;

import com.backfcdev.customersapirest.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
}
