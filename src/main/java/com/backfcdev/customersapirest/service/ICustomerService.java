package com.backfcdev.customersapirest.service;

import com.backfcdev.customersapirest.model.Customer;
import com.backfcdev.customersapirest.model.dto.CustomerDTO;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    Customer save(CustomerDTO customerDTO);
    Customer findById(Long id);
    Customer update(long id, CustomerDTO customerDTO);
    long deleteById(long id);
}
