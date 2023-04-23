package com.backfcdev.customersapirest.service;

import com.backfcdev.customersapirest.exception.CustomerNotFoundException;
import com.backfcdev.customersapirest.model.Customer;
import com.backfcdev.customersapirest.repository.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements ICustomerService{
    private final ICustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public Customer update(long id, Customer customer) {
        customerRepository.findById(id)
                .orElseThrow(CustomerNotFoundException::new);

        return customerRepository.save(customer);
    }

    @Override
    public void delete(long id) {
        customerRepository.findById(id)
                .orElseThrow(CustomerNotFoundException::new);
        customerRepository.deleteById(id);
    }
}
