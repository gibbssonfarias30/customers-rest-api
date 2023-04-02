package com.backfcdev.customersapirest.service;

import com.backfcdev.customersapirest.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    Customer save(Customer customer);
    Customer findById(Long id);
    Customer update(long id, Customer customer);
    void delete(long id);
}
