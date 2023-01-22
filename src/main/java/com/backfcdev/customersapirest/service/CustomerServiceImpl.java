package com.backfcdev.customersapirest.service;

import com.backfcdev.customersapirest.exception.CustomerNotFoundException;
import com.backfcdev.customersapirest.model.Customer;
import com.backfcdev.customersapirest.model.dto.CustomerDTO;
import com.backfcdev.customersapirest.repository.ICustomerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements ICustomerService{
    private final ModelMapper modelMapper = new ModelMapper();
    private final ICustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer save(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public Customer update(long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        modelMapper.map(customerDTO, customer);

        return customerRepository.save(customer);
    }

    @Override
    public long deleteById(long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        customerRepository.deleteById(customer.getId());
        return customer.getId();
    }
}
